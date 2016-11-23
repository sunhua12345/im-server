package utouu.im.net.tcp.mina.codec.work;

import org.apache.mina.core.session.IoSession;

import utouu.im.net.tcp.mina.entity.Message;
import utouu.im.obj.ObjectPool;
import utouu.im.thread.QueueWork;
import utouu.im.thread.WorkQueue;
import utouu.im.utils.ConstantUtils;

public class SendHearBeatWork extends QueueWork {
	private static final long serialVersionUID = -4800753687213006267L;
	private int code;
	private IoSession ioSession;
	private byte[] data;

	@Override
	public void init(Object... objs) throws Exception {
		this.code = (Integer) objs[0];
		this.ioSession = (IoSession) objs[1];
		this.data = (byte[]) objs[2];
	}

	@Override
	public void run() {
		Message message = (Message) ObjectPool.getPool().borrow(Message.class);
		message.setCode(code);
		message.setData(data);
		message.setLength(8 + data.length);
		ioSession.write(message);
	}

	@Override
	public WorkQueue getWorkQueue() {
		return new WorkQueue() {

			@Override
			public long getId() {
				return ConstantUtils.RES_HEARTBEAT_QUEUE;
			}
		};
	}

}
