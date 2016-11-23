package utouu.im.obj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utouu.im.config.Config;
import utouu.im.find.DynamicFind;



public class ObjectPool extends DynamicFind {
	private final int default_size = Config.getConfig().OBJECT_POOL_CACHE;
	public final static Map<String, List<BaseObj>> map = new HashMap<String, List<BaseObj>>();
	public static final Object obj = new Object();

	private ObjectPool() {
	}

	private static ObjectPool objectPool = new ObjectPool();

	public static ObjectPool getPool() {
		return objectPool;
	}

	public BaseObj borrow(Class<? extends BaseObj> clazz) {
		synchronized (obj) {
			String clazzName = clazz.getSimpleName();
			List<BaseObj> list = map.get(clazzName);
			if (list == null) {
				list = new ArrayList<BaseObj>(default_size);
				map.put(clazzName, list);
			}
			if (list.isEmpty()) {
				for (int i = 0; i < default_size; i++) {
					try {
						BaseObj baseObj = clazz.newInstance();
						list.add(baseObj);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			return list.remove(0);
		}
	}

	public void back(BaseObj baseObj) {
		synchronized (obj) {
			List<BaseObj> list = ObjectPool.map.get(baseObj.getClass()
					.getSimpleName());
			if (list == null) {
				list = new ArrayList<BaseObj>();
				map.put(baseObj.getClass().getSimpleName(), list);
			}
			baseObj.clearProp();// 清空属性
			if (list.size() < 2 * Config.getConfig().OBJECT_POOL_CACHE) {
				list.add(baseObj);
			} else {
				baseObj = null;
			}
		}
	}

	@Override
	public void findClass(Class<?> clz) throws Exception {
		String clazzName = clz.getSimpleName();
		List<BaseObj> list = map.get(clazzName);
		if (list == null) {
			list = new ArrayList<BaseObj>(default_size);
			map.put(clazzName, list);
		}
		if (list.isEmpty()) {
			for (int i = 0; i < default_size; i++) {
				try {
					BaseObj baseObj = (BaseObj) clz.newInstance();
					list.add(baseObj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean verification(Class<?> clazz) {
		return interfaceOn(clazz, BaseObj.class);
	}

	@Override
	public void findOver() {
		
	}

	@Override
	public void findInit() {
		// TODO Auto-generated method stub
		
	}
}
