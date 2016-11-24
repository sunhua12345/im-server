package utouu.im.net.tcp.mina.entity.vo;

public class AccountOnlineVO {
	private String account;
	private boolean online;
	private String hostIp;
	private int hostPort;

	public AccountOnlineVO() {
		super();
	}
	public AccountOnlineVO(String account, boolean online, String hostIp, int hostPort) {
		super();
		this.account = account;
		this.online = online;
		this.hostIp = hostIp;
		this.hostPort = hostPort;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public int getHostPort() {
		return hostPort;
	}
	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
	}
}
