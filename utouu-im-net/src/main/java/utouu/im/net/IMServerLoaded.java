package utouu.im.net;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import utouu.im.net.service.api.IZookeeperService;
import utouu.im.thread.WorkManager;

public class IMServerLoaded implements ApplicationContextAware {
	private static ApplicationContext context;
	@Autowired
	private IZookeeperService zookeeperService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
		WorkManager.getManager().submit(IMServerStart.class,zookeeperService);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<?> clz) {
		T t = (T) context.getBean(clz.getSimpleName());
		if (t == null) {
			String first = String.valueOf(clz.getSimpleName().charAt(0));
			String beanName = clz.getSimpleName().replaceFirst(first, first.toLowerCase());
			t = (T) context.getBean(beanName);
		}
		return t;
	}

}
