package utouu.im.net.tcp.mina.codec.work;

import utouu.im.net.tcp.mina.entity.Message;
import utouu.im.net.tcp.mina.process.ProcessManager;
import utouu.im.thread.QueueWork;
import utouu.im.thread.Work;
import utouu.im.thread.WorkManager;
import utouu.im.thread.WorkQueue;
import utouu.im.utils.ConstantUtils;

public class PutMsgWork extends QueueWork {
	private static final long serialVersionUID = 4232780310529607817L;
	private Message message;

	@Override
	public WorkQueue getWorkQueue() {
		return new WorkQueue() {

			public long getId() {
				return ConstantUtils.MESSAGE_IN_QUEUE;
			}
		};
	}

	public void init(Object... objs) throws Exception {
		this.message = (Message) objs[0];
	}

	@SuppressWarnings("unchecked")
	public void run() {
		Class<? extends Work> clazz = (Class<? extends Work>) ProcessManager.getManager().getProcess(message.getCode());
		if (clazz != null) {
			WorkManager.getManager().submit(clazz, message);
		}
	}

}
