package utouu.im.net.tcp.mina.node.process;

import org.apache.mina.core.session.IoSession;

import com.google.protobuf.GeneratedMessage.Builder;

import utouu.im.annotation.IProcess;
import utouu.im.net.tcp.mina.IMQueue;
import utouu.im.net.tcp.mina.node.ServerNodeInfo;
import utouu.im.net.tcp.mina.node.work.NodeClientBackConnectWork;
import utouu.im.net.tcp.mina.process.NetProcess;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.protobuf.pb.MsgNode.ReqNodeServerCreate;
import utouu.im.thread.WorkManager;
import utouu.im.thread.WorkQueue;

@IProcess(code = GameCode.REQ_NODE_SERVER_CREATE_VALUE)
public class ReqNodeServerCreateProcess extends NetProcess<ReqNodeServerCreate> {
	private static final long serialVersionUID = 9124140678624640214L;

	@Override
	public Builder<?> build() {
		return ReqNodeServerCreate.newBuilder();
	}

	@Override
	public void handle(IoSession session, ReqNodeServerCreate message)
			throws Exception {
		String serverHostIp = message.getServerip();
		int serverHostPort = message.getServerport();
		WorkManager.getManager().submit(NodeClientBackConnectWork.class,new ServerNodeInfo(serverHostIp, serverHostPort));
	}

	@Override
	public WorkQueue getWorkQueue() {
		return IMQueue.IM_NODE_QUEUE;
	}

}
