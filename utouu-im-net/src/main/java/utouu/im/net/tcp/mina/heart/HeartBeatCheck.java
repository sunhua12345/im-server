package utouu.im.net.tcp.mina.heart;

import org.quartz.JobDataMap;

import utouu.im.annotation.IInterval;
import utouu.im.thread.TimeWork;
import utouu.im.thread.WorkManager;
import utouu.im.thread.WorkQueue;
import utouu.im.utils.ConstantUtils;
@IInterval(interval = ConstantUtils.HEART_BEAT_CHECK)
public class HeartBeatCheck extends TimeWork{
	private static final long serialVersionUID = 1936917123869321162L;

	@Override
	public void execute(JobDataMap paramJobDataMap) {
		WorkManager.getManager().submit(CheckWork.class);
	}

	@Override
	public WorkQueue getWorkQueue() {
		return new WorkQueue() {
			
			@Override
			public long getId() {
				return ConstantUtils.TIMER_QUEUE;
			}
		};
	}

}
