package utouu.im.net.tcp.mina.node.work;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import utouu.im.net.tcp.mina.node.ServerNodeFactory;
import utouu.im.net.tcp.mina.node.ServerNodeInfo;
import utouu.im.net.tcp.mina.node.codec.MinaNodeCodecFactory;
import utouu.im.net.tcp.mina.node.codec.MinaNodeIMHandler;
import utouu.im.thread.AynWork;

public class NodeClientBackConnectWork extends AynWork {
	private static final long serialVersionUID = -5301958032662268735L;
	private ServerNodeInfo serverNode;

	@Override
	public void init(Object... objs) throws Exception {
		this.serverNode = (ServerNodeInfo) objs[0];
	}

	@Override
	public void run() {
		NioSocketConnector connector = new NioSocketConnector();
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		chain.addLast("codec", new ProtocolCodecFilter(new MinaNodeCodecFactory()));
		connector.setHandler(new MinaNodeIMHandler());
		connector.setConnectTimeoutCheckInterval(30);
		ConnectFuture future = connector.connect(new InetSocketAddress(serverNode.getNodeIp(),serverNode.getNodePort()));
		future.awaitUninterruptibly();
		IoSession nodeSession = null;
		try {
			nodeSession = future.getSession();
			IoSession old = ServerNodeFactory.openServerNodeSessions.put(serverNode.toString(), nodeSession);// 加入已开列表
			System.out.println("节点" + serverNode.toString() + "线路成功加入到服务器集群");
			if (old != null) {
				old.closeOnFlush();
				old = null;
			}
		} catch (Exception e) {
		} finally {
			if (future != null) {
				future.cancel();
			}
		}
	}

}
