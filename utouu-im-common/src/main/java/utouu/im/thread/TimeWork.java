package utouu.im.thread;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utouu.im.annotation.IInterval;


public abstract class TimeWork extends QueueWork {
	private static final long serialVersionUID = -2448950776438012061L;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected JobExecutionContext context;

	public abstract void execute(JobDataMap paramJobDataMap);

	public void init(Object... args) throws ClassCastException {
		this.context = ((JobExecutionContext) args[0]);
	}

	public long getInterval() {
		if (!this.getClass().isAnnotationPresent(IInterval.class)) {
			throw new RuntimeException("no interval!");
		}
		IInterval iInterval = this.getClass().getAnnotation(IInterval.class);
		return iInterval.interval();
	}

	public void run() {
		JobDataMap jobDataMap = this.context.getJobDetail().getJobDataMap();
		execute(jobDataMap);
	}
}
