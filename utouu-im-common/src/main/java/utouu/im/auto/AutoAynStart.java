package utouu.im.auto;

import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import utouu.im.annotation.IInterval;
import utouu.im.thread.AynWork;
import utouu.im.thread.TimeWork;
import utouu.im.thread.TimerCenter;

public abstract class AutoAynStart extends AynWork {
	private static final long serialVersionUID = 6709959211931662622L;

	public abstract long interval();

	public abstract Class<? extends TimeWork> getClz();

	@Override
	public void init(Object... objs) throws Exception {

	}

	@Override
	public void run() {
		if (getClz().isAnnotationPresent(IInterval.class)) {
			Trigger trigger = TriggerBuilder.newTrigger().startNow()
					.withIdentity(getClz().getSimpleName()).withSchedule(
							SimpleScheduleBuilder.simpleSchedule()
									.repeatForever()
									.withIntervalInMilliseconds(interval()))
					.build();
			TimerCenter.getCenter().schedueWork(trigger, getClz(), null);
		}
	}
}
