package utouu.im.net.tcp.mina.node;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

import utouu.im.net.tcp.mina.node.work.NodeServerHeartBeatWork;
import utouu.im.thread.WorkManager;


public class ServerNodeFactory {
	private ServerNodeFactory(){
	}
	private static ServerNodeFactory serverNodeFactory = new ServerNodeFactory();
	public static ServerNodeFactory getFactory(){
		return serverNodeFactory;
	}
	
	public static final Map<String, IoSession> openServerNodeSessions = new ConcurrentHashMap<String, IoSession>();// 已经开F列表

	public void start(){
		WorkManager.getManager().submit(NodeServerHeartBeatWork.class);
	}

	public void serverNodeClosed(IoSession ioSession) {
		for(Entry<String,IoSession> entry:openServerNodeSessions.entrySet()){
			if(entry.getValue().equals(ioSession)){
				System.out.println("节点" + entry.getKey() + "线路已从关闭并从集群中移除");
				openServerNodeSessions.remove(entry.getKey());
				return;
			}
		}
	}

	public IoSession getNodeServer(String ip, int port) {
		return openServerNodeSessions.get(ip+"-"+port);
	}
}
