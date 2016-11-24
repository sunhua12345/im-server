package utouu.im.net.tcp.mina.node.process;

import org.apache.mina.core.session.IoSession;

import com.google.protobuf.GeneratedMessage.Builder;

import utouu.im.annotation.IProcess;
import utouu.im.net.ServiceManager;
import utouu.im.net.service.api.INodeService;
import utouu.im.net.tcp.mina.IMQueue;
import utouu.im.net.tcp.mina.process.NetProcess;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.protobuf.pb.MsgNode.NotifyNodeSomeOnePrimaryChatToMe;
import utouu.im.thread.WorkQueue;
@IProcess(code = GameCode.NOTIFY_NODE_SOMEONE_PRIMARYCHAT_TO_ME_VALUE)
public class NotifyNodeSomeonePrimaryChatToMeProcess extends NetProcess<NotifyNodeSomeOnePrimaryChatToMe>{
	private static final long serialVersionUID = -6255822422907322297L;

	@Override
	public void handle(IoSession session, NotifyNodeSomeOnePrimaryChatToMe message) throws Exception {
		INodeService nodeService = ServiceManager.getService(INodeService.class);
		nodeService.notifySomeOnePrimaryChatToMe(session,message);
	}

	@Override
	public Builder<?> build() {
		return NotifyNodeSomeOnePrimaryChatToMe.newBuilder();
	}

	@Override
	public WorkQueue getWorkQueue() {
		return IMQueue.IM_NODE_QUEUE;
	}

}
