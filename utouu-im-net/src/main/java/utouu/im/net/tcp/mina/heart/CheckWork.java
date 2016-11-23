package utouu.im.net.tcp.mina.heart;

import utouu.im.net.tcp.mina.cache.SessionManager;
import utouu.im.thread.QueueWork;
import utouu.im.thread.WorkQueue;
import utouu.im.utils.ConstantUtils;

public class CheckWork extends QueueWork{
	private static final long serialVersionUID = -9007332991849273513L;

	@Override
	public void init(Object... objs) throws Exception {
		
	}

	@Override
	public void run() {
		long now = System.currentTimeMillis();
		SessionManager.doHeartBeatCheck(now);
	}

	@Override
	public WorkQueue getWorkQueue() {
		return new WorkQueue() {
			
			@Override
			public long getId() {
				return ConstantUtils.HEART_BEAT_CHECK;
			}
		};
	}

}
