package jweblite.util;

import java.io.BufferedInputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

public class PropertiesUtils {

	/**
	 * Load Properties
	 * 
	 * @param baseName
	 *            String
	 * @return Properties
	 */
	public static Properties loadProperties(String baseName) {
		if (baseName == null) {
			return null;
		}
		Properties prop = new Properties();
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(
					PropertiesUtils.class.getResourceAsStream(String.format(
							"/%s.properties", baseName)));
			prop.load(bis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(bis);
		}
		return prop;
	}

}