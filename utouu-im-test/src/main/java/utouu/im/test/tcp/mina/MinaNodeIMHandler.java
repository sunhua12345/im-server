package utouu.im.test.tcp.mina;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;

import utouu.im.net.tcp.mina.codec.work.PutMsgWork;
import utouu.im.net.tcp.mina.entity.Message;
import utouu.im.net.tcp.mina.node.ServerNodeFactory;
import utouu.im.net.tcp.mina.work.SessionCloseWork;
import utouu.im.thread.WorkManager;
import utouu.im.utils.ConstantUtils;

public class MinaNodeIMHandler implements IoHandler {

	@Override
	public void exceptionCaught(IoSession session, Throwable arg1)
			throws Exception {
		close(session);
	}

	private void close(IoSession session) {
		if (session.isConnected() || session.isActive() || !session.isClosing()) {
			System.out.println("#######CLOSED");
			ServerNodeFactory.getFactory().serverNodeClosed(session);
			session.closeNow();
		}		
	}

	@Override
	public void messageReceived(IoSession session, Object obj) throws Exception {
		if (obj != null && obj instanceof Message) {
			Message msg = (Message) obj;
			session.setAttribute(ConstantUtils.LAST_RECIVED_TIME, System.currentTimeMillis());
			WorkManager.getManager().submit(PutMsgWork.class, msg);
		}
	}

	@Override
	public void messageSent(IoSession session, Object arg1) throws Exception {
	}

	@Override
	public void sessionClosed(IoSession ioSession) throws Exception {
		close(ioSession);
	}

	@Override
	public void sessionCreated(IoSession ioSession) throws Exception {
		// CLOSED：无连接是活动的或正在进行
		// LISTEN：服务器在等待进入呼叫
		// SYN_RECV：一个连接请求已经到达，等待确认
		// SYN_SENT：应用已经开始，打开一个连接
		// ESTABLISHED：正常数据传输状态
		// FIN_WAIT1：应用说它已经完成
		// FIN_WAIT2：另一边已同意释放
		// ITMED_WAIT：等待所有分组死掉
		// CLOSING：两边同时尝试关闭
		// TIME_WAIT：另一边已初始化一个释放
		// LAST_ACK：等待所有分组死掉
		// netstat -an|findstr "7771"
		// 掉线使用新建立的socket进行三次握手,直接释放掉老的socket链接状态(来自于操作系统)
		SocketSessionConfig cfg = (SocketSessionConfig) ioSession.getConfig();
		cfg.setReceiveBufferSize(4 * 1024);
		cfg.setReadBufferSize(4 * 1024);
		cfg.setKeepAlive(true);
		cfg.setSoLinger(0); // CLOSE_WAIT状态直接关闭
		ioSession.setAttribute(ConstantUtils.LAST_RECIVED_TIME, System.currentTimeMillis());
	}

	@Override
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
	}

	@Override
	public void sessionOpened(IoSession ioSession) throws Exception {
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		
	}

}
