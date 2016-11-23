package utouu.im.net.tcp.mina.process;

import org.apache.mina.core.session.IoSession;

import com.google.protobuf.GeneratedMessage;

import utouu.im.net.tcp.mina.entity.Message;
import utouu.im.obj.ObjectPool;
import utouu.im.thread.QueueWork;

public abstract class NetProcess<T> extends QueueWork {
	private static final long serialVersionUID = 7068568986176399152L;
	private Message message;

	public abstract void handle(IoSession session, T message) throws Exception;

	@Override
	public void init(Object... objs) throws Exception {
		this.message = (Message) objs[0];
	}

	public abstract GeneratedMessage.Builder<?> build();

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		try {
			T msg = (T) build().mergeFrom(message.getData()).build();
			if (message.getCode() != 999997) {// 999997为心跳
				logger.info(
						"\n----------------------------RECIEVED----------------------------\nFROM:{}\nCODE:{}[{}]\n{}\n"
								+ "-----------------------------------------------------------------\n\n\n\n\n\n",
						message.getIoSession(), message.getCode(), message.getCode(), msg);
			}
			handle(message.getIoSession(), msg);
			ObjectPool.getPool().back(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
