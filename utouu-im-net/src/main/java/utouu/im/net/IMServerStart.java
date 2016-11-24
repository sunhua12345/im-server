package utouu.im.net;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import utouu.im.auto.AutoAynStartManager;
import utouu.im.auto.AutoStartManager;
import utouu.im.config.Config;
import utouu.im.net.service.api.IZookeeperService;
import utouu.im.net.tcp.mina.SessionFilterManager;
import utouu.im.net.tcp.mina.codec.MinaCodecFactory;
import utouu.im.net.tcp.mina.codec.MinaIMHandler;
import utouu.im.net.tcp.mina.node.ServerNodeFactory;
import utouu.im.net.tcp.mina.process.ProcessManager;
import utouu.im.obj.ObjectPool;
import utouu.im.thread.AynWork;
import utouu.im.thread.TimerCenter;

public class IMServerStart extends AynWork{
	private static final long serialVersionUID = -8991187596173301758L;
	private IZookeeperService zookeeperService;
	@Override
	public void init(Object... objs) throws Exception {
		this.zookeeperService = (IZookeeperService) objs[0];
		Config.getConfig().start();
		ObjectPool.getPool().start();
		ProcessManager.getManager().start();
		TimerCenter.getCenter().start();
		AutoStartManager.getManager().start();
		AutoAynStartManager.getManager().start();
		SessionFilterManager.getManager().start();
		ServiceManager.getManager().start();
		ServerNodeFactory.getFactory().start();
	}

	@Override
	public void run() {
		try {
			SocketAcceptor acceptor = new NioSocketAcceptor();
			DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
			chain.addLast("codec", new ProtocolCodecFilter(new MinaCodecFactory()));
			acceptor.setHandler(new MinaIMHandler());
			acceptor.bind(new InetSocketAddress(Config.getConfig().IM_SERVER_IP,Config.getConfig().IM_SERVER_PORT));
			logger.debug("START_SERVER BIND IP:{},LISTEN PORT:{}", Config.getConfig().IM_SERVER_IP,Config.getConfig().IM_SERVER_PORT);
			zookeeperService.serverInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}