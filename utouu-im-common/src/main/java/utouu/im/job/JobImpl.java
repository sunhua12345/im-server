package utouu.im.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import utouu.im.thread.TimeWork;
import utouu.im.thread.TimerCenter;
import utouu.im.thread.WorkManager;


public class JobImpl implements Job {
	@SuppressWarnings("unchecked")
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		Class<? extends TimeWork> clazz = (Class<? extends TimeWork>) context
				.getJobDetail().getJobDataMap().get(TimerCenter.WORK_CLAZZ);
		WorkManager.getManager().submit(clazz, context);
	}
}
