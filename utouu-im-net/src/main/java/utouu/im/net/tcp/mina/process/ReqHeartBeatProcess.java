package utouu.im.net.tcp.mina.process;

import org.apache.mina.core.session.IoSession;

import utouu.im.annotation.IProcess;
import utouu.im.net.tcp.mina.utils.IoSender;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.protobuf.pb.MsgCode.ReqHeartBeat;
import utouu.im.protobuf.pb.MsgCode.ResHeartBeat;
import utouu.im.thread.WorkQueue;
import utouu.im.utils.ConstantUtils;

import com.google.protobuf.GeneratedMessage.Builder;

@IProcess(code = GameCode.REQ_HEART_BEAT_VALUE)
public class ReqHeartBeatProcess extends NetProcess<ReqHeartBeat> {
	private static final long serialVersionUID = 5190621395802681078L;

	@Override
	public Builder<?> build() {
		return ReqHeartBeat.newBuilder();
	}

	@Override
	public WorkQueue getWorkQueue() {
		return new WorkQueue() {

			@Override
			public long getId() {
				return ConstantUtils.REQ_HEARBEAT_QUEUE;
			}
		};
	}

	@Override
	public void handle(IoSession session, ReqHeartBeat message) throws Exception {
		ResHeartBeat.Builder builder = ResHeartBeat.newBuilder();
		builder.setTime(System.currentTimeMillis());
		IoSender.sendHeartBeatMsg(session, builder);
	}

}
