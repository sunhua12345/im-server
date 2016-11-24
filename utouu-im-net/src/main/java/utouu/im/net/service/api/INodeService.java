package utouu.im.net.service.api;

import org.apache.mina.core.session.IoSession;

import utouu.im.protobuf.pb.MsgNode.NotifyNodeSomeOnePrimaryChatToMe;

public interface INodeService {
	public void notifySomeOnePrimaryChatToMe(IoSession session, NotifyNodeSomeOnePrimaryChatToMe message);
}
