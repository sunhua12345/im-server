package utouu.im.net.tcp.mina;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utouu.im.annotation.IPreFilter;
import utouu.im.find.DynamicFind;
import utouu.im.net.tcp.mina.filter.FreeSessionFilter;

public class SessionFilterManager extends DynamicFind {
	private final List<Class<? extends FreeSessionFilter>> filters = new ArrayList<Class<? extends FreeSessionFilter>>();

	private SessionFilterManager() {
	}

	private static SessionFilterManager freeSessionFilterManager = new SessionFilterManager();

	public static SessionFilterManager getManager() {
		return freeSessionFilterManager;
	}

	@Override
	public boolean verification(Class<?> clazz) {
		return interfaceOn(clazz, FreeSessionFilter.class) && annotationOn(clazz, IPreFilter.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void findClass(Class<?> clz) throws Exception {
		Class<? extends FreeSessionFilter> claes = (Class<? extends FreeSessionFilter>) clz;
		filters.add(claes);
	}

	@Override
	public void findOver() {
		Map<Integer,Class<? extends FreeSessionFilter>> map = new HashMap<Integer,Class<? extends FreeSessionFilter>>();
		for(Class<? extends FreeSessionFilter> filter:filters){
			IPreFilter iPreFilter = filter.getAnnotation(IPreFilter.class);
			int filterNum = iPreFilter.fileterNum();
			if(map.containsKey(filterNum)){
				throw new RuntimeException("拦截器下标重复");
			}
			map.put(filterNum, filter);
		}
		List<Integer> list = new ArrayList<Integer>();
		list.addAll(map.keySet());
		Collections.sort(list,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});
		filters.clear();
		for(int i=0;i<list.size();i++){
			filters.add(map.get(list.get(i)));
		}
	}

	public List<Class<? extends FreeSessionFilter>> getFilters() {
		return filters;
	}

	@Override
	public void findInit() {
		
	}
}
