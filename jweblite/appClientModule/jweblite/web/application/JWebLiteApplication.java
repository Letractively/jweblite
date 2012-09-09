package jweblite.web.application;

import java.io.Serializable;

import jweblite.web.JWebLiteFilterConfig;
import jweblite.web.dispatcher.JWebLiteRequestDispatchSettings;
import jweblite.web.dispatcher.JWebLiteRequestDispatcher;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteApplication implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log _cat = LogFactory
			.getLog(JWebLiteApplication.class);

	private static JWebLiteApplication application = new JWebLiteApplication();

	private JWebLiteFilterConfig filterConfig = null;
	private JWebLiteRequestDispatcher requestDispatcher = null;

	/**
	 * Default constructor.
	 */
	public JWebLiteApplication() {
		super();
	}

	/**
	 * Set
	 * 
	 * @return JWebLiteApplication
	 */
	public static void set(JWebLiteApplication application) {
		JWebLiteApplication.application = application;
	}

	/**
	 * Get
	 * 
	 * @return JWebLiteApplication
	 */
	public static JWebLiteApplication get() {
		return application;
	}

	/**
	 * Do Before Request
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            JWebLiteResponseWrapper
	 * @param reqDispatchSettings
	 *            JWebLiteRequestDispatchSettings
	 */
	public void doBeforeRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp,
			JWebLiteRequestDispatchSettings reqDispatchSettings) {
	}

	/**
	 * Do Before Render
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            JWebLiteResponseWrapper
	 */
	public void doBeforeRender(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) {
	}

	/**
	 * Do After Request
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            JWebLiteResponseWrapper
	 */
	public void doAfterRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) {
	}

	/**
	 * Destroy
	 */
	public void destroy() {
	}

	/**
	 * Get Filter Config
	 * 
	 * @return JWebLiteFilterConfig
	 */
	public JWebLiteFilterConfig getFilterConfig() {
		return filterConfig;
	}

	/**
	 * Set Filter Config
	 * 
	 * @param filterConfig
	 *            JWebLiteFilterConfig
	 */
	public void setFilterConfig(JWebLiteFilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * Get Request Dispatcher
	 * 
	 * @return JWebLiteRequestDispatcher
	 */
	public JWebLiteRequestDispatcher getRequestDispatcher() {
		return requestDispatcher;
	}

	/**
	 * Set Request Dispatcher
	 * 
	 * @param requestDispatcher
	 *            JWebLiteRequestDispatcher
	 */
	public void setRequestDispatcher(JWebLiteRequestDispatcher requestDispatcher) {
		this.requestDispatcher = requestDispatcher;
	}

}