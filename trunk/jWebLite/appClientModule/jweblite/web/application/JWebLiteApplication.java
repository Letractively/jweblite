package jweblite.web.application;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLiteFilterConfig;
import jweblite.web.dispatcher.JWebLiteRequestDispatcher;
import jweblite.web.wrapper.FormModel;

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
	 *            HttpServletRequest
	 * @param resp
	 *            HttpServletResponse
	 * @param formModel
	 *            FormModel
	 */
	public void doBeforeRequest(HttpServletRequest req,
			HttpServletResponse resp, FormModel formModel) {
	}

	/**
	 * Do Before Render
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param resp
	 *            HttpServletResponse
	 * @param formModel
	 *            FormModel
	 */
	public void doBeforeRender(HttpServletRequest req,
			HttpServletResponse resp, FormModel formModel) {
	}

	/**
	 * Do After Request
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param resp
	 *            HttpServletResponse
	 * @param formModel
	 *            FormModel
	 */
	public void doAfterRequest(HttpServletRequest req,
			HttpServletResponse resp, FormModel formModel) {
	}

	/**
	 * Do Error
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param resp
	 *            HttpServletResponse
	 * @param e
	 *            Throwable
	 */
	public void doError(HttpServletRequest req, HttpServletResponse resp,
			Throwable e) {
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