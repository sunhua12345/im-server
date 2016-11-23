package utouu.im.net.tcp.mina.process.impl.chat;

import com.google.protobuf.GeneratedMessage.Builder;

import utouu.im.annotation.IProcess;
import utouu.im.net.service.api.IChatService;
import utouu.im.net.tcp.mina.IMQueue;
import utouu.im.net.tcp.mina.SessionClient;
import utouu.im.net.tcp.mina.process.impl.SDKLoginIn;
import utouu.im.obj.ServiceManager;
import utouu.im.protobuf.pb.MsgChat.ReqSdkPrimaryTextChat;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.thread.WorkQueue;
@IProcess(code=GameCode.REQ_SDK_PRIMARYTEXTCHAT_VALUE)
public class ReqSdkPrimaryTextChatProcess extends SDKLoginIn<ReqSdkPrimaryTextChat>{
	private static final long serialVersionUID = 4513541991083632148L;

	@Override
	public void handleAfterLogin(SessionClient client, ReqSdkPrimaryTextChat message) {
		String chatText = message.getChatText();
		String otherAccount = message.getToAccount();
		IChatService chatService = ServiceManager.getService(IChatService.class);
		chatService.primaryTextChat(client,otherAccount,chatText);
	}

	@Override
	public Builder<?> build() {
		return ReqSdkPrimaryTextChat.newBuilder();
	}

	@Override
	public WorkQueue getWorkQueue() {
		return IMQueue.IM_ROLE_QUEUE;
	}

}
