package utouu.im.thread;

import java.util.HashMap;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.MutableTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utouu.im.annotation.IInterval;
import utouu.im.find.DynamicFind;
import utouu.im.job.JobImpl;


public class TimerCenter extends DynamicFind {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Scheduler scheduled;
	public static String WORK_CLAZZ = "WORK_CLAZZ";
	private final Map<Class<? extends TimeWork>, Long> timeWorks = new HashMap<Class<? extends TimeWork>, Long>();

	private TimerCenter() {
		try {
			scheduled = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	private static TimerCenter timerCenter = new TimerCenter();

	public static TimerCenter getCenter() {
		return timerCenter;
	}

	@Override
	public void start() {
		super.start();
		try {
			this.scheduled.start();
			logger.debug("Timer start!");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void shutdown() {
		try {
			this.scheduled.shutdown(true);
			logger.debug("Timer shutdown!");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void schedueWork(Trigger trigger, Class<? extends TimeWork> clazz,
			JobDataMap jobDataMap) {
		try {
			TriggerKey key = trigger.getKey();
			if (this.scheduled.checkExists(key)) {
				this.scheduled.rescheduleJob(key, trigger);
				return;
			}
			JobDetail jobDetail = JobBuilder.newJob(JobImpl.class)
					.withIdentity(key.getName(), key.getGroup()).build();
			jobDetail.getJobDataMap().put(WORK_CLAZZ, clazz);
			if (jobDataMap != null) {
				jobDetail.getJobDataMap().putAll(jobDataMap);
			}
			this.scheduled.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			throw new RuntimeException("schedueWork", e);
		}
	}

	public void schedueWork(String cron, Class<? extends TimeWork> clz,
			JobDataMap jobDataMap) throws InstantiationException,
			IllegalAccessException {
		MutableTrigger trigger = CronScheduleBuilder.cronSchedule(cron).build();
		trigger.setKey(TriggerKey.triggerKey(clz.getSimpleName()));
		schedueWork(trigger, clz, jobDataMap);
	}

	public void deleteTimerWork(JobKey jobKey) {
		try {
			this.scheduled.deleteJob(jobKey);
		} catch (SchedulerException e) {
			throw new RuntimeException("deleteTimerWork", e);
		}
	}

	public void deleteTimerWork(Class<? extends TimeWork> clz) {
		try {
			JobKey key = new JobKey(clz.getSimpleName());
			this.scheduled.deleteJob(key);
		} catch (SchedulerException e) {
			throw new RuntimeException("schedueWork", e);
		}
	}

	public long getInterval(Class<? extends TimeWork> clazz) {
		return timeWorks.get(clazz);
	}

	public boolean verification(Class<?> clazz) {
		return annotationOn(clazz, IInterval.class)
				&& superClassOn(clazz, TimeWork.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void findClass(Class<?> clz) throws Exception {
		Class<? extends TimeWork> clazz = (Class<? extends TimeWork>) clz;
		IInterval interval = clazz.getAnnotation(IInterval.class);
		timeWorks.put(clazz, interval.interval());
	}

	@Override
	public void findOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findInit() {
		// TODO Auto-generated method stub
		
	}
}
