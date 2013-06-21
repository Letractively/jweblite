package jweblite.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

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
		if (baseName == null || !baseName.startsWith("/")) {
			throw new IllegalArgumentException(
					"baseName sould be /packageName/propertiesFileName");
		}
		baseName = baseName.concat(".properties");

		InputStream is = PropertiesUtils.class.getResourceAsStream(baseName);
		if (is == null) {
			throw new RuntimeException(
					"Can't find property file for base name ".concat(baseName));
		}
		Properties prop = new Properties();
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(is);
			prop.load(bis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(bis);
		}
		return prop;
	}

	/**
	 * Load Properties
	 * 
	 * @param baseName
	 *            String
	 * @return Properties
	 */
	public static Properties loadPropertiesFromXML(String baseName) {
		if (baseName == null || !baseName.startsWith("/")) {
			throw new IllegalArgumentException(
					"baseName sould be /packageName/propertiesFileName");
		}
		baseName = baseName.concat(".xml");

		InputStream is = PropertiesUtils.class.getResourceAsStream(baseName);
		if (is == null) {
			throw new RuntimeException(
					"Can't find property file for base name ".concat(baseName));
		}
		Properties prop = new Properties();
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(is);
			prop.loadFromXML(bis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(bis);
		}
		return prop;
	}

	/**
	 * Load ResourceBundle
	 * 
	 * @param baseName
	 *            String
	 * @param locale
	 *            Locale
	 * @return ResourceBundle
	 */
	public static ResourceBundle loadResourceBundle(String baseName,
			Locale locale) {
		if (baseName == null || !baseName.startsWith("/")) {
			throw new IllegalArgumentException(
					"baseName sould be /packageName/bundleFileName");
		}
		return ResourceBundle.getBundle(baseName.substring(1), locale);
	}

}