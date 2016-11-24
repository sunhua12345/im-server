package utouu.im.net.service;

import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import utouu.im.config.Config;
import utouu.im.net.GlobalServerSender.ServerNotify;
import utouu.im.net.service.api.IZookeeperService;
import utouu.im.net.tcp.mina.entity.vo.AccountOnlineVO;
import utouu.im.net.zookeeper.ZookeeperTool;
@Service(value="IZookeeperService")
public class ZookeeperService extends BaseService implements IZookeeperService{
	private static String hostname;
	public static String address;
	private static String pid;
	public static int port = Config.getConfig().IM_SERVER_PORT;
	
	static {
		try {
			hostname = InetAddress.getLocalHost().getHostName();
			address = InetAddress.getLocalHost().getHostAddress();
			pid = ManagementFactory.getRuntimeMXBean().getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void serverInit() {
//		try {
//			//初始化znode节点名称为online_accounts的临时节点
//				ZookeeperTool.createZnode("/sunhua/online/"+address+"-"+port+"/online_accounts", CreateMode.EPHEMERAL, ZooDefs.Ids.OPEN_ACL_UNSAFE);
//				if(!ZookeeperTool.checkZnode("/sunhua/online/"+address+"-"+port+"/online_accounts")){
//			}
//			if(!ZookeeperTool.checkZnode("/sunhua/broad/server_notify")){
//				//初始化znode节点名称为server_notify
//				ZookeeperTool.createZnode("/sunhua/broad/server_notify", CreateMode.EPHEMERAL, ZooDefs.Ids.OPEN_ACL_UNSAFE);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	@Override
	public void updateOnlineAccounts(String onlinesKeysString) {
		try {
			ZookeeperTool.setData("/sunhua/online/"+address+"-"+port+"/online_accounts", onlinesKeysString.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public AccountOnlineVO checkAccountOnline(String account) {
		AccountOnlineVO vo = null;
		Map<String,Set<String>> allOnlineMap = new HashMap<String,Set<String>>();
		try {
			List<String> online_server_children = ZookeeperTool.getChildren("/sunhua/online/");
			for(String children:online_server_children){
				byte[] bytes = ZookeeperTool.getData("/sunhua/online/"+children);
				String nodeOnlineAccountString = new String(bytes,"UTF-8");
				Map<String,Set<String>> map = JSONObject.parseObject(nodeOnlineAccountString,new TypeReference<Map<String,Set<String>>>() {
				});
				allOnlineMap.putAll(map);
			}
			for(Entry<String,Set<String>> entry:allOnlineMap.entrySet()){
				String address = entry.getKey().split("-")[0];
				int port = Integer.valueOf(entry.getKey().split("-")[1]);
				Set<String> set = entry.getValue();
				if(set.contains(account)){
					//在线
					vo = new AccountOnlineVO(account,true,address,port);
					System.out.println(account+"账号在服务器:"+address+"在线");
					return vo;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	@Override
	public void server_notify(ServerNotify notify) throws UnsupportedEncodingException, Exception {
		ZookeeperTool.setData("/sunhua/broad/server_notify", JSONObject.toJSONString(notify).getBytes("UTF-8"));
	}

}
