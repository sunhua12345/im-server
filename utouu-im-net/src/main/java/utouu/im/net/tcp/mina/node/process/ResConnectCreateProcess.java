package utouu.im.net.tcp.mina.node.process;

import org.apache.mina.core.session.IoSession;

import com.google.protobuf.GeneratedMessage.Builder;

import utouu.im.annotation.IProcess;
import utouu.im.net.tcp.mina.IMQueue;
import utouu.im.net.tcp.mina.process.NetProcess;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.protobuf.pb.MsgCode.ResConnectCreat;
import utouu.im.thread.WorkQueue;

@IProcess(code = GameCode.RES_CONNECT_CREATE_VALUE)
public class ResConnectCreateProcess extends NetProcess<ResConnectCreat> {
	private static final long serialVersionUID = 4287327510282426703L;

	@Override
	public Builder<?> build() {
		return ResConnectCreat.newBuilder();
	}

	@Override
	public void handle(IoSession session, ResConnectCreat message)
			throws Exception {
		System.out.println("链接服务器节点成功");
	}

	@Override
	public WorkQueue getWorkQueue() {
		return IMQueue.IM_NODE_QUEUE;
	}

}
