package utouu.im.net.service;

import utouu.im.annotation.IService;
import utouu.im.net.GlobalServerSender;
import utouu.im.net.service.api.IChatService;
import utouu.im.net.tcp.mina.SessionClient;
import utouu.im.net.tcp.mina.cache.ServerCache;
import utouu.im.net.tcp.mina.utils.IoSender;
import utouu.im.protobuf.pb.MsgChat.ResSdkPrimaryTextChat;
import utouu.im.protobuf.pb.MsgChat.ResSdkSomeOnePrimaryTextChatToMe;
import utouu.im.protobuf.pb.MsgCode.GameCode;
@IService
public class ChatService extends BaseService implements IChatService{

	@Override
	public void primaryTextChat(SessionClient client, String otherAccount, String chatText) {
		ResSdkPrimaryTextChat.Builder builder = ResSdkPrimaryTextChat.newBuilder();
		builder.setPrimaryTextChatResult(0);
		boolean onlineCurNode = ServerCache.checkOnlineCurrentZnode(otherAccount);
		if(onlineCurNode){
			SessionClient otherClient = ServerCache.getClient(otherAccount);
			ResSdkSomeOnePrimaryTextChatToMe.Builder builder2 = ResSdkSomeOnePrimaryTextChatToMe.newBuilder();
			builder2.setChatText(chatText);
			builder2.setFromAccount(client.getAccount());
			IoSender.sendMsg(otherClient, GameCode.RES_SDK_SOMEONEPRIMARYTEXTCHATTOME, builder2);
		}else{
			//当前节点不在线,可能在其它服务器节点,通过zookeeper来进行数据一致性操作
			boolean online = GlobalServerSender.getOnlineCheckAllZnode(otherAccount);
			if(online){
				//账号在其它znode在线,通过zookeeper协调一致性,发起推送消息
			}else{
				//所有znode节点都不在线,进行消息入库,保证下次用户上线能够拉取数据
			}
		}
		IoSender.sendMsg(client, GameCode.RES_SDK_PRIMARYTEXTCHAT, builder);
	}

}
