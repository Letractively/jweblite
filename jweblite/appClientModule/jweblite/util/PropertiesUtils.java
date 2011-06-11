package jweblite.util;

import java.io.BufferedInputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesUtils {

	private Log log = LogFactory.getLog(this.getClass());

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