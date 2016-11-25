package utouu.im.test;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import utouu.im.config.Config;
import utouu.im.obj.ObjectPool;
import utouu.im.test.tcp.mina.MinaNodeCodecFactory;
import utouu.im.test.tcp.mina.MinaNodeIMHandler;

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
			System.out.println("##########"+nodeSession);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (future != null) {
				future.cancel();
			}
		}
	}
}
