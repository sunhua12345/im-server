package utouu.im.net.tcp.mina.process.impl.chat;

import com.google.protobuf.GeneratedMessage.Builder;

import utouu.im.annotation.IProcess;
import utouu.im.net.ServiceManager;
import utouu.im.net.service.api.IChatService;
import utouu.im.net.tcp.mina.IMQueue;
import utouu.im.net.tcp.mina.SessionClient;
import utouu.im.net.tcp.mina.process.impl.SDKLoginIn;
import utouu.im.protobuf.pb.MsgChat.ReqSdkPrimaryChat;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.thread.WorkQueue;

@IProcess(code = GameCode.REQ_SDK_PRIMARY_CHAT_VALUE)
public class ReqSdkPrimaryChatProcess extends SDKLoginIn<ReqSdkPrimaryChat> {
	private static final long serialVersionUID = 4513541991083632148L;

	@Override
	public void handleAfterLogin(SessionClient client, ReqSdkPrimaryChat message) {
		IChatService chatService = ServiceManager.getService(IChatService.class);
		chatService.primaryChat(client, message);
	}

	@Override
	public Builder<?> build() {
		return ReqSdkPrimaryChat.newBuilder();
	}

	@Override
	public WorkQueue getWorkQueue() {
		return IMQueue.IM_ROLE_QUEUE;
	}

}
