package utouu.im.net.tcp.mina.node.work;

import org.apache.mina.core.session.IoSession;

import utouu.im.net.tcp.mina.node.ServerNodeFactory;
import utouu.im.net.tcp.mina.utils.IoSender;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.protobuf.pb.MsgCode.ReqHeartBeat;
import utouu.im.thread.AynWork;
import utouu.im.utils.ConstantUtils;

public class NodeServerHeartBeatWork extends AynWork {
	private static final long serialVersionUID = -2686582450295006740L;

	@Override
	public void init(Object... objs) throws Exception {

	}

	@Override
	public void run() {
		while (true) {
			try {
				ReqHeartBeat.Builder builder = ReqHeartBeat.newBuilder();
				for (IoSession ioSession : ServerNodeFactory.openServerNodeSessions.values()) {
					if (ioSession.isConnected()) {
						IoSender.sendMsg(ioSession, GameCode.REQ_HEART_BEAT, builder);
					}
				}
				Thread.sleep(ConstantUtils.TCP_HEARBEAT_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
