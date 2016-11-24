package utouu.im.net.tcp.mina.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONObject;

import utouu.im.net.ServiceManager;
import utouu.im.net.service.ZookeeperService;
import utouu.im.net.service.api.IZookeeperService;
import utouu.im.net.tcp.mina.SessionClient;

public class ServerCache {
	private ServerCache() {
	}
	private static IZookeeperService zookeeperService = ServiceManager.getService(IZookeeperService.class);
	private static final Map<String,SessionClient> onlines = new ConcurrentHashMap<String,SessionClient>();
	public static SessionClient clientOnline(SessionClient client){
		if(!onlines.containsKey(client.getAccount())){
			onlines.put(client.getAccount(), client);
			String onlinesKeysString = getOnlinesKeysString(onlines);
			//加入对应znode节点zookeeper账号上线
			zookeeperService.updateOnlineAccounts(onlinesKeysString);
		}
		return client;
	}
	private static String getOnlinesKeysString(Map<String, SessionClient> onlines) {
		Map<String,Set<String>> map = new HashMap<String,Set<String>>();
		Set<String> result = onlines.keySet();
		map.put(ZookeeperService.address+"-"+ZookeeperService.port, result);
		return JSONObject.toJSONString(map);
	}
	public static SessionClient clientOffline(SessionClient client){
		if(onlines.containsKey(client.getAccount())){
			client = onlines.remove(client.getAccount());
			String onlinesKeysString = getOnlinesKeysString(onlines);
			zookeeperService.updateOnlineAccounts(onlinesKeysString);
		}
		return client;
	}
	public static boolean checkOnlineCurrentZnode(String account) {
		return onlines.containsKey(account);
	}
	public static SessionClient getClient(String account) {
		if(onlines.containsKey(account)){
			return onlines.get(account);
		}
		return null;
	}
}
