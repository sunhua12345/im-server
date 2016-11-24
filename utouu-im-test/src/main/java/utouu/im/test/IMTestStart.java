package utouu.im.test;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import utouu.im.auto.AutoAynStartManager;
import utouu.im.auto.AutoStartManager;
import utouu.im.config.Config;
import utouu.im.net.ServiceManager;
import utouu.im.net.service.ZookeeperService;
import utouu.im.net.tcp.mina.SessionFilterManager;
import utouu.im.net.tcp.mina.node.ServerNodeFactory;
import utouu.im.net.tcp.mina.node.codec.MinaNodeCodecFactory;
import utouu.im.net.tcp.mina.node.codec.MinaNodeIMHandler;
import utouu.im.net.tcp.mina.utils.IoSender;
import utouu.im.obj.ObjectPool;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.protobuf.pb.MsgNode.ReqNodeServerCreate;
import utouu.im.thread.TimerCenter;

public class IMTestStart {
	public static void main(String[] args) {
		Config.getConfig().start();
		ObjectPool.getPool().start();
		NioSocketConnector connector = new NioSocketConnector();
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		chain.addLast("codec", new ProtocolCodecFilter(new MinaNodeCodecFactory()));
		connector.setHandler(new MinaNodeIMHandler());
		connector.setConnectTimeoutCheckInterval(30000);
		ConnectFuture future = connector.connect(new InetSocketAddress("192.168.121.53",8887));
		IoSession nodeSession = null;
		future.awaitUninterruptibly();
		try {
			nodeSession = future.getSession();
			ReqNodeServerCreate.Builder builder = ReqNodeServerCreate.newBuilder();
			builder.setServerip(ZookeeperService.address);
			builder.setServerport(ZookeeperService.port);
			IoSender.sendMsg(nodeSession, GameCode.REQ_NODE_SERVER_CREATE,builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (future != null) {
				future.cancel();
			}
		}
	}
}
