package utouu.im.net.service;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import utouu.im.net.service.api.INodeService;
import utouu.im.net.tcp.mina.SessionClient;
import utouu.im.net.tcp.mina.cache.ServerCache;
import utouu.im.net.tcp.mina.utils.IoSender;
import utouu.im.protobuf.pb.MsgChat.NotifySomeOnePrimaryChatToMe;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.protobuf.pb.MsgNode.NotifyNodeSomeOnePrimaryChatToMe;
@Service(value = "INodeService")
public class NodeService extends BaseService implements INodeService{

	@Override
	public void notifySomeOnePrimaryChatToMe(IoSession session, NotifyNodeSomeOnePrimaryChatToMe message) {
		String toAccount = message.getToAccount();
		SessionClient toClient = ServerCache.getClient(toAccount);
		if(toClient==null){
			//已从该节点下线
			return;
		}
		NotifySomeOnePrimaryChatToMe.Builder builder = NotifySomeOnePrimaryChatToMe.newBuilder();
		builder.setFromAccount(message.getFromAccount());
		builder.setMediaBytes(message.getMediaBytes());
		builder.setText(message.getText());
		builder.setType(message.getType());
		IoSender.sendMsg(toClient, GameCode.NOTIFY_SOMEONE_PRIMARY_CHAT_TO_ME, builder);
	}

}
