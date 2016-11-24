package utouu.im.net.tcp.mina.node.work;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import utouu.im.net.service.ZookeeperService;
import utouu.im.net.tcp.mina.node.ServerNodeFactory;
import utouu.im.net.tcp.mina.node.ServerNodeInfo;
import utouu.im.net.tcp.mina.node.codec.MinaNodeCodecFactory;
import utouu.im.net.tcp.mina.node.codec.MinaNodeIMHandler;
import utouu.im.net.tcp.mina.utils.IoSender;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.protobuf.pb.MsgNode.ReqNodeServerCreate;
import utouu.im.thread.AynWork;

public class NodeClientConnectWork extends AynWork{
	private static final long serialVersionUID = -8202328668669093596L;
	private ServerNodeInfo serverNodeInfo;
	@Override
	public void init(Object... objs) throws Exception {
		this.serverNodeInfo = (ServerNodeInfo) objs[0];
	}

	@Override
	public void run() {
		NioSocketConnector connector = new NioSocketConnector();
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		chain.addLast("codec", new ProtocolCodecFilter(new MinaNodeCodecFactory()));
		connector.setHandler(new MinaNodeIMHandler());
		connector.setConnectTimeoutCheckInterval(30);
		System.out.println("###########我的地址:"+ZookeeperService.address+"-"+ZookeeperService.port);
		System.out.println("###########要链接的地址:"+serverNodeInfo.toString());
		ConnectFuture future = connector.connect(new InetSocketAddress(serverNodeInfo.getNodeIp(),serverNodeInfo.getNodePort()));
		IoSession nodeSession = null;
		future.awaitUninterruptibly();
		try {
			nodeSession = future.getSession();
			IoSession old = ServerNodeFactory.openServerNodeSessions.put(serverNodeInfo.toString(), nodeSession);// 加入已开列表
			System.out.println("节点" + serverNodeInfo.toString() + "线路成功加入到服务器集群");
			ReqNodeServerCreate.Builder builder = ReqNodeServerCreate.newBuilder();
			builder.setServerip(ZookeeperService.address);
			builder.setServerport(ZookeeperService.port);
			IoSender.sendMsg(nodeSession, GameCode.REQ_NODE_SERVER_CREATE,builder);
			if (old != null) {
				old.closeOnFlush();
				old = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (future != null) {
				future.cancel();
			}
		}
	}

}
