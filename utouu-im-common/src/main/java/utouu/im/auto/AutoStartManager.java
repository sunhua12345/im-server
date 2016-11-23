package utouu.im.auto;

import utouu.im.find.DynamicFind;
import utouu.im.thread.TimeWork;
import utouu.im.thread.TimerCenter;

public class AutoStartManager extends DynamicFind {
	private AutoStartManager() {

	}

	private static AutoStartManager autoStartManager = new AutoStartManager();

	public static AutoStartManager getManager() {
		return autoStartManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void findClass(Class<?> clz) throws Exception {
		AutoStart autoStart = (AutoStart) clz.newInstance();
		String cron = autoStart.cron();
		TimerCenter.getCenter().schedueWork(cron,
				(Class<? extends TimeWork>) clz, null);
	}

	@Override
	public boolean verification(Class<?> clazz) {
		return superClassOn(clazz, TimeWork.class)
				&& interfaceOn(clazz, AutoStart.class);
	}

	@Override
	public void findOver() {
		
	}

	@Override
	public void findInit() {
		// TODO Auto-generated method stub
		
	}
}
