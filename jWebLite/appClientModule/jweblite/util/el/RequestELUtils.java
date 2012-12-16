package jweblite.util.el;

import java.util.Date;

import jweblite.data.MultiValueMap;
import jweblite.web.wrapper.FormModel;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestELUtils {

	private static final Log _cat = LogFactory.getLog(RequestELUtils.class);

	/**
	 * ========================================================================
	 * jweblite.web.wrapper.FormModel
	 * ========================================================================
	 */

	/**
	 * Contains Parameter
	 * 
	 * @param formModel
	 *            FormModel
	 * @param name
	 *            String
	 * @return boolean
	 */
	public static boolean containsParameter(FormModel formModel, String name) {
		return formModel.containsParameter(name);
	}

	/**
	 * Get Parameter Map
	 * 
	 * @param formModel
	 *            FormModel
	 * @return MultiValueMap
	 */
	public static MultiValueMap getParameterMap(FormModel formModel) {
		return formModel.getParameterMap();
	}

	/**
	 * Get String
	 * 
	 * @param formModel
	 *            FormModel
	 * @param name
	 *            String
	 * @param nullValue
	 *            String
	 * @return String
	 */
	public static String getString(FormModel formModel, String name,
			String nullValue) {
		return formModel.getString(name, nullValue);
	}

	/**
	 * Get Escaped String
	 * 
	 * @param formModel
	 *            FormModel
	 * @param name
	 *            String
	 * @param nullValue
	 *            String
	 * @return String
	 */
	public static String getEscapedString(FormModel formModel, String name,
			String nullValue) {
		return formModel.getEscapedString(name, nullValue);
	}

	/**
	 * Get Int
	 * 
	 * @param formModel
	 *            FormModel
	 * @param name
	 *            String
	 * @param errorValue
	 *            int
	 * @return int
	 */
	public static int getInt(FormModel formModel, String name, int errorValue) {
		return formModel.getInt(name, errorValue);
	}

	/**
	 * Get Long
	 * 
	 * @param formModel
	 *            FormModel
	 * @param name
	 *            String
	 * @param errorValue
	 *            int
	 * @return int
	 */
	public static long getLong(FormModel formModel, String name, long errorValue) {
		return formModel.getLong(name, errorValue);
	}

	/**
	 * Get Double
	 * 
	 * @param formModel
	 *            FormModel
	 * @param name
	 *            String
	 * @param errorValue
	 *            double
	 * @return double
	 */
	public static double getDouble(FormModel formModel, String name,
			double errorValue) {
		return formModel.getDouble(name, errorValue);
	}

	/**
	 * Get Date
	 * 
	 * @param formModel
	 *            FormModel
	 * @param name
	 *            String
	 * @param pattern
	 *            String
	 * @param errorValue
	 *            Date
	 * @return Date
	 */
	public static Date getDate(FormModel formModel, String name,
			String pattern, Date errorValue) {
		return formModel.getDate(name, pattern, errorValue);
	}

	/**
	 * Get File
	 * 
	 * @param formModel
	 *            FormModel
	 * @param name
	 *            String
	 * @return FileItem
	 */
	public static FileItem getFile(FormModel formModel, String name) {
		return formModel.getFile(name);
	}

}