package utouu.im.net.tcp.mina.filter.impl;

import org.apache.mina.core.session.IoSession;

import utouu.im.annotation.IPreFilter;
import utouu.im.net.tcp.mina.SessionClient;
import utouu.im.net.tcp.mina.cache.ServerCache;
import utouu.im.net.tcp.mina.filter.FreeSessionFilter;
import utouu.im.utils.ConstantUtils;
@IPreFilter(fileterNum=10)
public class LoginOutCacheClear implements FreeSessionFilter{

	@Override
	public Object doFilter(IoSession session, Object obj) {
		SessionClient client = (SessionClient) session.getAttribute(ConstantUtils.UNIQUE_CLIENT_ID);
		if(client!=null){
			client = ServerCache.clientOffline(client);
		}
		return client;
	}

}
