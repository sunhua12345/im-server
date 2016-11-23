package utouu.im.net.tcp.mina;

import org.apache.mina.core.session.IoSession;

public class SessionClient {
	private IoSession ioSession;
	private String account;

	public SessionClient(String account, IoSession ioSession) {
		this.account = account;
		this.ioSession = ioSession;
	}

	public IoSession getIoSession() {
		return ioSession;
	}

	public void setIoSession(IoSession ioSession) {
		this.ioSession = ioSession;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
