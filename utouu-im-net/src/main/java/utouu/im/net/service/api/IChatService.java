package utouu.im.net.service.api;

import utouu.im.net.tcp.mina.SessionClient;
import utouu.im.protobuf.pb.MsgChat.ReqSdkPrimaryChat;

public interface IChatService {

	public void primaryChat(SessionClient client, ReqSdkPrimaryChat message);

}
