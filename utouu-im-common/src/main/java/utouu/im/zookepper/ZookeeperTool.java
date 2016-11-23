package utouu.im.zookepper;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utouu.im.thread.AynWork;
import utouu.im.thread.WorkManager;




public class ZookeeperTool {
	private static final Logger logger = LoggerFactory.getLogger(ZookeeperTool.class);
	public static CuratorFramework zkClient;
	private static String hostname;
	private static String address;
	private static String pid;
	public static final String root="/utouu-uhoo/client";
	private static String queueName = "ssss";
	private static final Object obj = new Object();
	public static Map<String,InterProcessMutex> locks=new ConcurrentHashMap<String,InterProcessMutex>();
	static {
		try {
			hostname = InetAddress.getLocalHost().getHostName();
			address = InetAddress.getLocalHost().getHostAddress();
			pid = ManagementFactory.getRuntimeMXBean().getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**********************************************ZOOKEEPER_OPERATE**********************************/
	public static  void createZnode(String zNoodePath,CreateMode mode,List<ACL> acls) throws Exception{
		String node = null;
		if(zNoodePath.startsWith("/")){
			node = root+zNoodePath;
		}else{
			node = root+"/"+zNoodePath;
		}
		synchronized (node.intern()) {
			Stat stat = zkClient.checkExists().forPath(node);
			if (stat == null) {
				zkClient.create()// 创建一个路径
						.creatingParentsIfNeeded()// 如果指定的节点的父节点不存在，递归创建父节点
						.withMode(mode)// 存储类型（临时的还是持久的）
						.withACL(acls)// 访问权限
						.forPath(node);// 创建的路径
				logger.debug("创建临时子节点:" + node );
			}
		}
	}
	
	public static boolean checkZnode(String zNoodePath) throws Exception{
		String node = null;
		if(zNoodePath.startsWith("/")){
			node = root+zNoodePath;
		}else{
			node = root+"/"+zNoodePath;
		}
		synchronized (node.intern()) {
			Stat stat = zkClient.checkExists().forPath(node);
			if (stat == null) {
				return false;
			}else{
				return true;
			}
		}
	}

	public static void deleteZnode(String zNoodePath) throws Exception{
		String node = null;
		if(zNoodePath.startsWith("/")){
			node = root+zNoodePath;
		}else{
			node = root+"/"+zNoodePath;
		}
		synchronized (node.intern()) {
			Stat stat = zkClient.checkExists().forPath(node);
			if (stat != null) {
				zkClient.delete().deletingChildrenIfNeeded().forPath(node);
				logger.debug("删除子节点:" + node );
			}
		}
	}
	
	public static byte[] getData(String zNoodePath) throws Exception{
		String node = null;
		byte[] result = null;
		if(zNoodePath.startsWith("/")){
			node =root+zNoodePath;
		}else{
			node = root+"/"+zNoodePath;
		}
		synchronized (node.intern()) {
			Stat stat = zkClient.checkExists().forPath(node);
			if (stat != null) {
				result = zkClient.getData().forPath(node);
				logger.debug("获得子节点数据:" + node );
			}
		}
		return result;
	}
	
	public static void setData(String zNoodePath,byte[] data) throws Exception{
		String node = null;
		if(zNoodePath.startsWith("/")){
			node =root+zNoodePath;
		}else{
			node = root+"/"+zNoodePath;
		}
		synchronized (node.intern()) {
			Stat stat = zkClient.checkExists().forPath(node);
			if (stat != null) {
				zkClient.setData().forPath(node, data);
				logger.debug("获得子节点数据:" + node );
			}else{
				//递归创建子节点
				createZnode(zNoodePath, CreateMode.EPHEMERAL, ZooDefs.Ids.OPEN_ACL_UNSAFE);
				zkClient.setData().forPath(node, data);
			}
		}
	}
	
	public static List<String> getChildren(String zNoodePath) throws Exception{
		List<String> children = new ArrayList<String>();
		String node = null;
		if(zNoodePath.startsWith("/")){
			node= root+zNoodePath;
		}else{
			node = root+"/"+zNoodePath;
		}
		synchronized (node.intern()) {
			Stat stat = zkClient.checkExists().forPath(node);
			if (stat != null) {
				children.addAll(zkClient.getChildren().forPath(node));
				logger.debug("获得子节点路径:" + node );
			}
		}
		return children;
	}
	
	public static int getClientNum(String zNoodePath) {
		try {
			List<String> list = getChildren(zNoodePath);
			if (list!=null) {
				logger.debug("获取节点总数:{}", list.size());
				return list.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int getClientIndex(String zNoodePath) {
		try {
			List<String> list = getChildren(zNoodePath);
			if (list!=null) {
				int index = list.indexOf(hostname);
				logger.debug("{}执行定时任务,当前集群节[{}]点数:{},本机序号：{}", pid, list, list.size(), index);
				return index;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**********************************************ZOOKEEPER_PROCESS_LOCK**********************************/
	
	public static Object handleLockWork(String lock,LockMethod lockMethod,Object...objs) throws Exception{
		Object obj = null;
		InterProcessMutex mutex = locks.get(lock);
		if(mutex==null){
			mutex = new InterProcessMutex(zkClient, root+"/lock/"+lock);//初始化锁资源
		}
		try {
			if(mutex.acquire(5000, TimeUnit.MILLISECONDS)){
				locks.put(lock,mutex);//加入锁资源列表
				obj = lockMethod.lockMethod(objs);
			}else{
				throw new RuntimeException("获取锁资源失败，请稍后重试. ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(mutex!=null){
				mutex.release();
				if(locks.containsKey(lock)){
					locks.remove(lock);
				}
			}
		}
    	return obj;
	}

	public abstract interface LockMethod{
		public Object lockMethod(Object...objs) throws Exception;
	}



	/**********************************************ZOOKEEPER_PROCESS_QUEUE**********************************/
	public static void pushQueue(final String name,final String data) throws Exception{ 
		Object obj = handleLockWork("FIFO_WRITE_LOCK", new LockMethod() {
			@Override
			public Object lockMethod(Object... objs) throws Exception {
				if(checkZnode("/"+queueName+"/"+name)){
					//队列已满
					return "重复添加";
				}
				setData(queueName+"/"+name, data.getBytes());
				return "添加成功";
			}
		});
	}

	public static Object popQueue() throws Exception{
		Object obj = handleLockWork("FIFO_READ_LOCK", new LockMethod(){

			@Override
			public Object lockMethod(Object... objs) throws Exception {
				if(!checkZnode(queueName)){
					//队列已满
					return "队列不存在";
				}
				List<String> childrenPaths = getChildren(queueName);
				if(childrenPaths==null||childrenPaths.isEmpty()){
					//队列中已没有元素
					return "队列中已没有元素";
				}
				String firstChildPath = childrenPaths.get(0);
				System.out.println(firstChildPath);
				byte[] data = getData(queueName+"/"+firstChildPath);
				String result = new String(data,"UTF-8");
				deleteZnode(queueName+"/"+firstChildPath);
				return result;
			}
		});
		return obj;
	}
	
	
	private final static Map<String, QueueWorker> workers = new ConcurrentHashMap<String, QueueWorker>();
	
	public final static class QueueWorker extends AynWork{
		private static final long serialVersionUID = 1373574492116542530L;
		private String queueName;
		public QueueWorker(String queueName) {
			this.queueName = queueName;
		}

		@Override
		public void init(Object... objs) throws Exception {
			
		}

		@Override
		public void run() {
			synchronized (obj) {
				while(true){
					try {
						int num = getClientNum(queueName);
						if(num<=0){
							//阻塞
							obj.wait();
						}
						Object obj = popQueue();
						System.out.println("################队列中数据取出:"+obj);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		
	}
	
	
	
	
	public static void initZkClient(CuratorFramework client) {
		try { 
			zkClient = client;
			
			System.out.println("正在创建初始化zookeeper链接状态监听器");
			zkClient.getConnectionStateListenable().addListener(new ZkConnectListener());
			
			zkClient.start();
			
			 /**
	         * 监听数据节点的变化情况
	         */
	        final NodeCache nodeCache = new NodeCache(zkClient, root, false);
	        nodeCache.start(true);
	        nodeCache.getListenable().addListener(
	            new NodeCacheListener() {

					@Override
					public void nodeChanged() throws Exception {
						 System.out.println("Node data is changed, new data: " + 
			                        new String(nodeCache.getCurrentData().getData()));						
					}
	            }
	        );
	        
	        /**
	         * 监听子节点的变化情况
	         */
	        final PathChildrenCache queueListener = new PathChildrenCache(zkClient, root+"/"+queueName, true);
	        queueListener.start(StartMode.POST_INITIALIZED_EVENT);
	        queueListener.getListenable().addListener(
	            new PathChildrenCacheListener() {

					@Override
					public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
						 switch (event.getType()) {
	                        case CHILD_ADDED:
	                            System.out.println("CHILD_ADDED: " + event.getData().getPath());
	                            String path = event.getData().getPath();
	                            if(path.startsWith(root + "/"+queueName)){
	                            	//队列中节点添加
	                            	QueueWorker worker = workers.get(queueName);
	                            	if(worker==null){
	                            		QueueWorker queueWorker = new QueueWorker(queueName);
	                            		workers.put(queueName, queueWorker);
	                            		WorkManager.getManager().submit(queueWorker);
	                            	}else{
		                            	synchronized (obj) {
											obj.notifyAll();
										}
		                            }
	                            }
	                            break;
	                        case CHILD_REMOVED:
	                            System.out.println("CHILD_REMOVED: " + event.getData().getPath());
	                            break;
	                        case CHILD_UPDATED:
	                            System.out.println("CHILD_UPDATED: " + event.getData().getPath());
	                            byte[] data = event.getData().getData();
	                            System.out.println("#################"+new String(data,"UTF-8"));
	                            break;
	                        default:
	                            break;						
						}
		            }
	            }
	            
	        );
			
			
			
			
			logger.debug("正在创建初始化zookeeper服务器根节点{}",root+hostname+address);
			createZnode(hostname+address,CreateMode.EPHEMERAL,ZooDefs.Ids.OPEN_ACL_UNSAFE);//创建服务器znode节点
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
