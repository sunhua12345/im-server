package utouu.im.net.tcp.mina.process;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import utouu.im.annotation.IProcess;
import utouu.im.find.DynamicFind;

public class ProcessManager extends DynamicFind {
	private static final Map<Integer, Class<?>> processClazzMap = new ConcurrentHashMap<Integer, Class<?>>();

	private ProcessManager() {
	}

	private static ProcessManager processManager = new ProcessManager();

	public static ProcessManager getManager() {
		return processManager;
	}

	public boolean verification(Class<?> clazz) {
		return annotationOn(clazz, IProcess.class);
	}

	@Override
	public void findClass(Class<?> clz) throws Exception {
		IProcess iProcess = clz.getAnnotation(IProcess.class);
		processClazzMap.put(iProcess.code(), clz);
	}

	public Class<?> getProcess(int code) {
		return processClazzMap.get(code);
	}

	@Override
	public void findOver() {
		
	}

	@Override
	public void findInit() {
		// TODO Auto-generated method stub
		
	}
}
