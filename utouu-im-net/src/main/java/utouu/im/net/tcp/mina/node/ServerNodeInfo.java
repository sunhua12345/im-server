package utouu.im.net.tcp.mina.node;

public class ServerNodeInfo {
	private String nodeIp;
	private int nodePort;
	public ServerNodeInfo(String hostIp, int port) {
		this.nodeIp = hostIp;
		this.nodePort = port;
	}
	public String getNodeIp() {
		return nodeIp;
	}
	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}
	public int getNodePort() {
		return nodePort;
	}
	public void setNodePort(int nodePort) {
		this.nodePort = nodePort;
	}
	@Override
	public String toString() {
		return this.nodeIp+"-"+this.nodePort;
	}
}
