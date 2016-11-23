package utouu.im.net.tcp.mina.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import utouu.im.net.tcp.mina.SessionClient;

public class ServerCache {
	private ServerCache() {
	}
	private static final Map<String,SessionClient> onlines = new ConcurrentHashMap<String,SessionClient>();
	public static SessionClient clientOnline(SessionClient client){
		if(!onlines.containsKey(client.getAccount())){
			onlines.put(client.getAccount(), client);
		}
		return client;
	}
	public static SessionClient clientOffline(SessionClient client){
		if(onlines.containsKey(client.getAccount())){
			client = onlines.remove(client.getAccount());
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
