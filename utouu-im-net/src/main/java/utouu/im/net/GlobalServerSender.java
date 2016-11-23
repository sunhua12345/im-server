package utouu.im.net;

import utouu.im.net.tcp.mina.SessionClient;
import utouu.im.net.tcp.mina.cache.ServerCache;
import utouu.im.net.tcp.mina.utils.IoSender;
import utouu.im.protobuf.pb.MsgChat.ResSdkPrimaryTextChat;
import utouu.im.protobuf.pb.MsgChat.ResSdkSomeOnePrimaryTextChatToMe;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.protobuf.pb.MsgServer.ResManagerSomeOnePrimaryTextChatToMe;

public class GlobalServerSender {

	public static void sendMsg(String toAccount,String chatText) {
		boolean onlineCurNode = ServerCache.checkOnlineCurrentZnode(toAccount);
		if(onlineCurNode){
			SessionClient otherClient = ServerCache.getClient(toAccount);
			ResManagerSomeOnePrimaryTextChatToMe.Builder builder2 = ResManagerSomeOnePrimaryTextChatToMe.newBuilder();
			builder2.setChatText(chatText);
			IoSender.sendMsg(otherClient, GameCode.RES_MANAGER_SOMEONEPRIMARYTEXTCHATTOME, builder2);
		}else{
			//当前节点不在线,可能在其它服务器节点,通过zookeeper来进行数据一致性操作
			boolean online = getOnlineCheckAllZnode(toAccount);
			if(online){
				//账号在其它znode在线,通过zookeeper协调一致性,发起推送消息
			}else{
				//所有znode节点都不在线,进行消息入库,保证下次用户上线能够拉取数据
			}
		}
	}

	public static boolean getOnlineCheckAllZnode(String toAccount) {
		return false;
	}

}
