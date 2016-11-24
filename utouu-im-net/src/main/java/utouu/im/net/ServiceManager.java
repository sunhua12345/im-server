package utouu.im.net;

public class ServiceManager{
	private ServiceManager() {
	}

	private static ServiceManager serviceManager = new ServiceManager();

	public static ServiceManager getManager() {
		return serviceManager;
	}

	public static <T> T getService(Class<?> clz) {
		return IMServerLoaded.getInstance(clz);
	}

	public void start() {
		
	}
}
