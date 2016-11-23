package utouu.im.net.tcp.mina.filter;

import org.apache.mina.core.session.IoSession;

public interface FreeSessionFilter {
	public Object doFilter(IoSession session,Object obj);
}
