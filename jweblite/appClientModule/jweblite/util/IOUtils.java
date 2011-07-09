package jweblite.util;

import java.io.Closeable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IOUtils {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * Close Quietly
	 * 
	 * @param out
	 *            Closeable
	 */
	public static void closeQuietly(Closeable out) {
		if (out != null) {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}