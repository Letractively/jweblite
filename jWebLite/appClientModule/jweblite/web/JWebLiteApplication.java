package jweblite.web;

import java.io.Serializable;

import jweblite.web.dispatcher.JWebLiteRequestDispatcher;
import jweblite.web.page.FormModel;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

public class JWebLiteApplication implements Serializable {
	private static final long serialVersionUID = 1L;

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
	 * @param context
	 *            WebContext
	 * @param formModel
	 *            FormModel
	 * @throws SkipException
	 */
	public void doBeforeRequest(WebContext context, FormModel formModel)
			throws SkipException {
	}

	/**
	 * Do Before Render
	 * 
	 * @param context
	 *            WebContext
	 * @param formModel
	 *            FormModel
	 */
	public void doBeforeRender(WebContext context, FormModel formModel) {
	}

	/**
	 * Do After Request
	 * 
	 * @param context
	 *            WebContext
	 * @param formModel
	 *            FormModel
	 */
	public void doAfterRequest(WebContext context, FormModel formModel) {
	}

	/**
	 * Do Error
	 * 
	 * @param context
	 *            WebContext
	 * @param e
	 *            Throwable
	 */
	public void doError(WebContext context, Throwable e) {
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