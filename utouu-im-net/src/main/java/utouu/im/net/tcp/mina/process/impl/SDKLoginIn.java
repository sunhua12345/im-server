package utouu.im.net.tcp.mina.process.impl;

import org.apache.mina.core.session.IoSession;

import utouu.im.net.tcp.mina.SessionClient;
import utouu.im.net.tcp.mina.process.NetProcess;
import utouu.im.utils.ConstantUtils;

public abstract class SDKLoginIn<T> extends NetProcess<T>{
	private static final long serialVersionUID = -8705240654985964433L;

	@Override
	public void handle(IoSession session, T message) throws Exception {
		SessionClient sessionClient = (SessionClient) session.getAttribute(ConstantUtils.UNIQUE_CLIENT_ID);
		if(sessionClient==null){
			//还未登录
			return;
		}
		handleAfterLogin(sessionClient,message);
	}
	
	public abstract void handleAfterLogin(SessionClient client,T message);

}
