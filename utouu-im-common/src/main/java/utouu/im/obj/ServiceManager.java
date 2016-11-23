package utouu.im.obj;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import utouu.im.annotation.IService;
import utouu.im.find.DynamicFind;




public class ServiceManager extends DynamicFind{
	private ServiceManager() {
	}

	private static ServiceManager serviceManager = new ServiceManager();

	public static ServiceManager getManager() {
		return serviceManager;
	}

	private final Map<String, Object> services = new ConcurrentHashMap<String, Object>();

	public boolean verification(Class<?> clazz) {
		return annotationOn(clazz, IService.class)
				&& clazz.getInterfaces().length > 0;
	}

	@Override
	public void findClass(Class<?> clz) throws Exception {
		Class<?> clazz = clz.getInterfaces()[0];
		services.put(clazz.getSimpleName(), clz.newInstance());
	}

	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<?> clz) {
		return (T) services.get(clz.getSimpleName());
	}

	public static <T> T getService(Class<?> clz) {
		return serviceManager.getInstance(clz);
	}

	@Override
	public void findOver() {
		
	}

	@Override
	public void findInit() {
		
	}
}
