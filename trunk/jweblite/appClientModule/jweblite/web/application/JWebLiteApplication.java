package jweblite.web.application;

import java.io.Serializable;

import jweblite.web.JWebLiteFilterConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteApplication implements Serializable {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private static JWebLiteApplication application = new JWebLiteApplication();

	private JWebLiteFilterConfig filterConfig = null;
	private Object initClass = null;

	/**
	 * Default constructor.
	 */
	private JWebLiteApplication() {
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
	 * Get Init Class
	 * 
	 * @return Object
	 */
	public Object getInitClass() {
		return initClass;
	}

	/**
	 * Set Init Class
	 * 
	 * @param initClass
	 *            Object
	 */
	public void setInitClass(Object initClass) {
		this.initClass = initClass;
	}

}