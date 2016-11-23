package utouu.im.bean.dto.utils;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class StringHelper {
	public static AtomicInteger getUniqueID = new AtomicInteger(1000001);

	public static String buildKey(Object... objs) {
		StringBuffer sb = new StringBuffer();
		for (Object obj : objs) {
			sb.append(obj.toString()).append("-");
		}
		return sb.toString();
	}

	public static String buildKey(Collection<String> objs) {
		StringBuffer sb = new StringBuffer();
		for (Object obj : objs) {
			sb.append(obj.toString()).append("-");
		}
		return sb.toString();
	}

	public static String randUUID() {
		UUID uuid = UUID.randomUUID();
		String s = uuid.toString().replace("-", "");
		return s;
	}

	public static boolean isNullOrEmpty(String tvClientId) {
		if (tvClientId == null) {
			return true;
		}
		if ("".equals(tvClientId)) {
			return true;
		}
		return false;
	}
}
