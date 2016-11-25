package utouu.im.net.service;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.protobuf.ByteString;

import utouu.im.net.service.api.IChatService;
import utouu.im.net.service.api.IZookeeperService;
import utouu.im.net.tcp.mina.SessionClient;
import utouu.im.net.tcp.mina.cache.ServerCache;
import utouu.im.net.tcp.mina.entity.vo.AccountOnlineVO;
import utouu.im.net.tcp.mina.node.ServerNodeFactory;
import utouu.im.net.tcp.mina.utils.IoSender;
import utouu.im.protobuf.pb.MsgChat.NotifySomeOnePrimaryChatToMe;
import utouu.im.protobuf.pb.MsgChat.ReqSdkPrimaryChat;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.protobuf.pb.MsgNode.NotifyNodeSomeOnePrimaryChatToMe;
@Service(value="IChatService")
public class ChatService extends BaseService implements IChatService{
	@Autowired
	private IZookeeperService zookeeperService;
	@Override
	public void primaryChat(SessionClient client, ReqSdkPrimaryChat message) {
		String toAccount = message.getToAccount();
		int type = message.getType();
		String text = message.getText();
		ByteString byteString = message.getMediaBytes();
		AccountOnlineVO vo = zookeeperService.checkAccountOnline(toAccount);
		if(vo!=null){
			//在线
			String localServerIpPort = ZookeeperService.address+"-"+ZookeeperService.port;
			String temp = vo.getHostIp()+"-"+vo.getHostPort();
			if(localServerIpPort.equals(temp)){
				SessionClient to = ServerCache.getClient(toAccount);
				if(to!=null){
					//在本服务器节点,直接推送消息
					NotifySomeOnePrimaryChatToMe.Builder builder = NotifySomeOnePrimaryChatToMe.newBuilder();
					builder.setFromAccount(client.getAccount());
					builder.setType(type);
					builder.setText(text);
					builder.setMediaBytes(byteString);
					IoSender.sendMsg(to, GameCode.NOTIFY_SOMEONE_PRIMARY_CHAT_TO_ME, builder);
				}else{
					//不在线,在这里可以异步保存离线信息
				}
			}else{
				//在其它服务器节点,跨服务器推送消息
				String ip = vo.getHostIp();
				int port = vo.getHostPort();
				IoSession nodeSession = ServerNodeFactory.getFactory().getNodeServer(ip,port);
				NotifyNodeSomeOnePrimaryChatToMe.Builder builder = NotifyNodeSomeOnePrimaryChatToMe.newBuilder();
				builder.setFromAccount(client.getAccount());
				builder.setMediaBytes(byteString);
				builder.setServerip(ZookeeperService.address);
				builder.setServerport(ZookeeperService.port);
				builder.setText(text);
				builder.setToAccount(toAccount);
				builder.setType(type);
				IoSender.sendMsg(nodeSession, GameCode.NOTIFY_NODE_SOMEONE_PRIMARYCHAT_TO_ME, builder);
			}
		}else{
			//不在线,在这里可以异步保存离线信息
		}
	}

}
