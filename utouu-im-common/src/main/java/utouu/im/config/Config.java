package utouu.im.config;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Properties;

public class Config {
	// #COMMON
	public int OBJECT_POOL_CACHE;
	public String PACKAGE_SCAN_PATH;
	public String IM_SERVER_IP;
	public int IM_SERVER_PORT;
	public boolean LOG_OPEN;

	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	private Config() {
	}

	private static Config config = new Config();
	private final static Properties p = new Properties();

	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////
	public static Config getConfig() {
		return config;
	}

	public synchronized static Properties getP() {
		return p;
	}

	static {
		loadConfig();
	}

	public void start() {
	}

	public static void loadConfig() {
		try {
			InputStream in = Config.class.getClassLoader().getResourceAsStream(
					"Config.properties");
			if (in == null) {
				return;
			}
			getP().clear();
			getP().load(in);
			Field[] fields = Config.class.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				Type type = field.getGenericType();
				if (field.getType().equals(Config.class)) {
					continue;
				}
				if (field.getType().equals(Properties.class)) {
					continue;
				}
				if (field.getType().equals(InputStream.class)) {
					continue;
				}
				if (!getP().containsKey(fieldName)) {
					throw new RuntimeException(
							"config load error!Config.properties not contain "
									+ fieldName);
				}
				String value = getP().getProperty(fieldName);
				if (type.toString().toUpperCase().endsWith("INT")) {
					field.setInt(config, Integer.valueOf(value));
				} else if (type.toString().toUpperCase().endsWith("LONG")) {
					field.setLong(config, Long.valueOf(value));
				} else if (type.toString().toUpperCase().endsWith("FLOAT")) {
					field.setFloat(config, Float.valueOf(value));
				} else if (type.toString().toUpperCase().endsWith("BOOLEAN")) {
					field.setBoolean(config, Boolean.valueOf(value));
				} else if (type.toString().toUpperCase().endsWith("DOUBLE")) {
					field.setDouble(config, Double.valueOf(value));
				} else {
					field.set(config, value);
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("load "+config.getClass().getSimpleName());
	}
}
