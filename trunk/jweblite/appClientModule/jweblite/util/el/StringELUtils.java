package jweblite.util.el;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringELUtils {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * concat
	 * 
	 * @param str
	 *            String
	 * @param appendStr
	 *            String
	 * @return String
	 */
	public static String concat(String str, String appendStr) {
		if (str == null) {
			return null;
		}
		return str.concat(appendStr);
	}

}