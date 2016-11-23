package utouu.im.net.tcp.mina.process.impl.login;

import org.apache.mina.core.session.IoSession;

import com.google.protobuf.GeneratedMessage.Builder;

import utouu.im.annotation.IProcess;
import utouu.im.net.service.api.ILoginService;
import utouu.im.net.tcp.mina.IMQueue;
import utouu.im.net.tcp.mina.SessionClient;
import utouu.im.net.tcp.mina.cache.ServerCache;
import utouu.im.net.tcp.mina.entity.vo.OffChatMsgDetail;
import utouu.im.net.tcp.mina.process.NetProcess;
import utouu.im.net.tcp.mina.utils.IoSender;
import utouu.im.obj.ServiceManager;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin;
import utouu.im.protobuf.pb.MsgLogin.ResSdkLogin;
import utouu.im.thread.WorkQueue;
import utouu.im.utils.ConstantUtils;
@IProcess(code=GameCode.REQ_SDK_LOGIN_VALUE)
public class ReqSdkLoginProcess extends NetProcess<ReqSdkLogin>{
	private static final long serialVersionUID = 2902075813088753502L;

	@Override
	public void handle(IoSession session, ReqSdkLogin message) throws Exception {
		String utouuImAccount = message.getUtouuImAccount();
		String utouuImPwd = message.getUtouuImPwd();
		//验证
		SessionClient sessionClient = new SessionClient(utouuImAccount, session);
		session.setAttribute(ConstantUtils.UNIQUE_CLIENT_ID,sessionClient);
		sessionClient = ServerCache.clientOnline(sessionClient);//上线
		ResSdkLogin.Builder builder = ResSdkLogin.newBuilder();
		builder.setLoginResult(0);
		IoSender.sendMsg(sessionClient, GameCode.RES_SDK_LOGIN, ResSdkLogin.newBuilder().setLoginResult(0));
		//拉取数据库离线消息,并将消息推送给玩家
		ILoginService loginService = ServiceManager.getService(ILoginService.class);
		OffChatMsgDetail detail = loginService.pullOffLineChatMsgDetail(sessionClient.getAccount());
		
	}

	@Override
	public Builder<?> build() {
		return ReqSdkLogin.newBuilder();
	}

	@Override
	public WorkQueue getWorkQueue() {
		return IMQueue.IM_LOGIN_QUEUE;
	}

}
