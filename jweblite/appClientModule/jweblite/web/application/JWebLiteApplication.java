package jweblite.web.application;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLiteFilterConfig;
import jweblite.web.dispatcher.JWebLiteRequestDispatchSettings;
import jweblite.web.dispatcher.JWebLiteRequestDispatcher;
import jweblite.web.wrapper.JWebLiteRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteApplication implements Serializable {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	public static JWebLiteApplication application = new JWebLiteApplication();

	private JWebLiteFilterConfig filterConfig = null;
	private JWebLiteRequestDispatcher requestDispatcher = null;

	/**
	 * Default constructor.
	 */
	public JWebLiteApplication() {
		super();
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
	 *            HttpServletResponse
	 * @param reqDispatchSettings
	 *            JWebLiteRequestDispatchSettings
	 */
	public void doBeforeRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp,
			JWebLiteRequestDispatchSettings reqDispatchSettings) {
	}

	/**
	 * Do Before Render
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            HttpServletResponse
	 */
	public void doBeforeRender(JWebLiteRequestWrapper req,
			HttpServletResponse resp) {
	}

	/**
	 * Do After Request
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            HttpServletResponse
	 */
	public void doAfterRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp) {
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