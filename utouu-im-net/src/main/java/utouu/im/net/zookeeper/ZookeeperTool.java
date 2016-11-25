package utouu.im.net.zookeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utouu.im.net.service.ZookeeperService;
import utouu.im.net.tcp.mina.node.ServerNodeInfo;
import utouu.im.net.tcp.mina.node.work.NodeClientConnectWork;
import utouu.im.net.tcp.mina.work.ServerNotifyWork;
import utouu.im.thread.WorkManager;

public class ZookeeperTool {
	private static final Logger logger = LoggerFactory.getLogger(ZookeeperTool.class);
	public static CuratorFramework zkClient;
	public static Map<String, InterProcessMutex> locks = new ConcurrentHashMap<String, InterProcessMutex>();
	private static PathChildrenCache queueListener = null;
	private static NodeCache nodeCache = null;

	/********************************************** ZOOKEEPER_OPERATE **********************************/
	public static void createZnode(String zNoodePath, CreateMode mode, List<ACL> acls) throws Exception {
		synchronized (zNoodePath.intern()) {
			Stat stat = zkClient.checkExists().forPath(zNoodePath);
			if (stat == null) {
				zkClient.create()// 创建一个路径
						.creatingParentsIfNeeded()// 如果指定的节点的父节点不存在，递归创建父节点
						.withMode(mode)// 存储类型（临时的还是持久的）
						.withACL(acls)// 访问权限
						.forPath(zNoodePath);// 创建的路径
				logger.debug("创建临时子节点:" + zNoodePath);
			}
		}
	}

	public static boolean checkZnode(String zNoodePath) throws Exception {
		synchronized (zNoodePath.intern()) {
			Stat stat = zkClient.checkExists().forPath(zNoodePath);
			if (stat == null) {
				return false;
			} else {
				return true;
			}
		}
	}

	public static void deleteZnode(String zNoodePath) throws Exception {
		synchronized (zNoodePath.intern()) {
			Stat stat = zkClient.checkExists().forPath(zNoodePath);
			if (stat != null) {
				zkClient.delete().deletingChildrenIfNeeded().forPath(zNoodePath);
				logger.debug("删除子节点:" + zNoodePath);
			}
		}
	}

	public static byte[] getData(String zNoodePath) throws Exception {
		byte[] result = null;
		synchronized (zNoodePath.intern()) {
			Stat stat = zkClient.checkExists().forPath(zNoodePath);
			if (stat != null) {
				result = zkClient.getData().forPath(zNoodePath);
				logger.debug("获得子节点数据:" + zNoodePath);
			}
		}
		return result;
	}

	public static void setData(String zNoodePath, byte[] data) throws Exception {
		synchronized (zNoodePath.intern()) {
			Stat stat = zkClient.checkExists().forPath(zNoodePath);
			if (stat != null) {
				zkClient.setData().forPath(zNoodePath, data);
				logger.debug("获得子节点数据:" + zNoodePath);
			} else {
				// 递归创建子节点
				createZnode(zNoodePath, CreateMode.EPHEMERAL, ZooDefs.Ids.OPEN_ACL_UNSAFE);
				zkClient.setData().forPath(zNoodePath, data);
			}
		}
	}

	public static List<String> getChildren(String zNoodePath) throws Exception {
		List<String> children = new ArrayList<String>();
		synchronized (zNoodePath.intern()) {
			Stat stat = zkClient.checkExists().forPath(zNoodePath);
			if (stat != null) {
				children.addAll(zkClient.getChildren().forPath(zNoodePath));
				logger.debug("获得子节点路径:" + zNoodePath);
			}
		}
		return children;
	}

	public static int getClientNum(String zNoodePath) {
		try {
			List<String> list = getChildren(zNoodePath);
			if (list != null) {
				logger.debug("获取节点总数:{}", list.size());
				return list.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getClientIndex(String zNoodePath, String hostname, String pid) {
		try {
			List<String> list = getChildren(zNoodePath);
			if (list != null) {
				int index = list.indexOf(hostname);
				logger.debug("{}执行定时任务,当前集群节[{}]点数:{},本机序号：{}", pid, list, list.size(), index);
				return index;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	
	public static void main(String[] args) throws Exception {
		Object obj = ZookeeperTool.handleLockWork("/sassadas", new LockMethod() {
			
			@Override
			public Object lockMethod(Object... objs) throws Exception {
				int param = (int) objs[0];
				
				return 1;
			}
		}, 1);
	}
	/********************************************** ZOOKEEPER_PROCESS_LOCK **********************************/

	public static Object handleLockWork(String lock, LockMethod lockMethod, Object... objs) throws Exception {
		Object obj = null;
		InterProcessMutex mutex = locks.get(lock);
		if (mutex == null) {
			mutex = new InterProcessMutex(zkClient, lock);// 初始化锁资源
		}
		try {
			if (mutex.acquire(5000, TimeUnit.MILLISECONDS)) {
				locks.put(lock, mutex);// 加入锁资源列表
				obj = lockMethod.lockMethod(objs);
			} else {
				throw new RuntimeException("获取锁资源失败，请稍后重试. ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mutex != null) {
				mutex.release();
				if (locks.containsKey(lock)) {
					locks.remove(lock);
				}
			}
		}
		return obj;
	}

	public abstract interface LockMethod {
		public Object lockMethod(Object... objs) throws Exception;
	}

	public static void initZkClient(CuratorFramework client) {
		try {
			zkClient = client;

			System.out.println("正在创建初始化zookeeper链接状态监听器");
			zkClient.getConnectionStateListenable().addListener(new ZkConnectListener());

			zkClient.start();

			nodeCache = new NodeCache(zkClient, "/sunhua/broad/server_notify", false);
			nodeCache.start(true);
			nodeCache.getListenable().addListener(new NodeCacheListener() {

				@Override
				public void nodeChanged() throws Exception {
					System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
					System.out.println(
							"Node data is changed, new data: " + new String(nodeCache.getCurrentData().getData()));
					String data = new String(nodeCache.getCurrentData().getData());
					WorkManager.getManager().submit(ServerNotifyWork.class, data);
				}
			});

			queueListener = new PathChildrenCache(zkClient, "/sunhua/online", true);
			queueListener.start(StartMode.POST_INITIALIZED_EVENT);
			queueListener.getListenable().addListener(new PathChildrenCacheListener() {

				@Override
				public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
					switch (event.getType()) {
					case CHILD_ADDED:
						System.out.println("CHILD_ADDED: " + event.getData().getPath());
						String path = event.getData().getPath().substring("/sunhua/online/".length());
						String server_path = ZookeeperService.address + "-" + ZookeeperService.port;
						if (!path.equals(server_path)) {
							// 新节点加入到集群中
							String hostIp = path.split("-")[0];
							int port = Integer.valueOf(path.split("-")[1]);
							ServerNodeInfo serverNodeInfo = new ServerNodeInfo(hostIp, port);
							System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%");
							WorkManager.getManager().submit(NodeClientConnectWork.class, serverNodeInfo);
						}
						break;
					case CHILD_REMOVED:
						System.out.println("CHILD_REMOVED: " + event.getData().getPath());
						break;
					case CHILD_UPDATED:
						System.out.println("CHILD_UPDATED: " + event.getData().getPath());
						break;
					default:
						break;
					}
				}
			});
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
