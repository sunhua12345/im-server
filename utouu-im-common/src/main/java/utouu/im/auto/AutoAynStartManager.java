package utouu.im.auto;

import utouu.im.find.DynamicFind;
import utouu.im.thread.WorkManager;
import utouu.im.thread.Work;

public class AutoAynStartManager extends DynamicFind {
	private AutoAynStartManager() {

	}

	private static AutoAynStartManager autoAynStartManager = new AutoAynStartManager();

	public static AutoAynStartManager getManager() {
		return autoAynStartManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void findClass(Class<?> clz) throws Exception {
		WorkManager.getManager().submit((Class<? extends Work>) clz);
	}

	@Override
	public boolean verification(Class<?> clazz) {
		return superClassOn(clazz, AutoAynStart.class);
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
