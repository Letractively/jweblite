package jweblite.util.el;

import java.util.Date;

import jweblite.web.wrapper.JWebLiteRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestELUtils {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * ========================================================================
	 * jweblite.web.wrapper.JWebLiteRequestWrapper
	 * ========================================================================
	 */

	/**
	 * Get Parameter
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param name
	 *            String
	 * @param nullValue
	 *            String
	 * @return String
	 */
	public static String getParameter(JWebLiteRequestWrapper req, String name,
			String nullValue) {
		return req.getParameter(name, nullValue);
	}

	/**
	 * Get Html Parameter
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param name
	 *            String
	 * @param nullValue
	 *            String
	 * @return String
	 */
	public static String getHtmlParameter(JWebLiteRequestWrapper req,
			String name, String nullValue) {
		return req.getHtmlParameter(name, nullValue);
	}

	/**
	 * Get Int Parameter
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param name
	 *            String
	 * @param errorValue
	 *            int
	 * @return int
	 */
	public static int getIntParameter(JWebLiteRequestWrapper req, String name,
			int errorValue) {
		return req.getIntParameter(name, errorValue);
	}

	/**
	 * Get Double Parameter
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param name
	 *            String
	 * @param errorValue
	 *            double
	 * @return double
	 */
	public static double getDoubleParameter(JWebLiteRequestWrapper req,
			String name, double errorValue) {
		return req.getDoubleParameter(name, errorValue);
	}

	/**
	 * Get Date Parameter
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param name
	 *            String
	 * @param pattern
	 *            String
	 * @param errorValue
	 *            Date
	 * @return Date
	 */
	public static Date getDateParameter(JWebLiteRequestWrapper req,
			String name, String pattern, Date errorValue) {
		return req.getDateParameter(name, pattern, errorValue);
	}

}