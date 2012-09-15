package jweblite.web;

import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import jweblite.util.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteFilterConfig implements FilterConfig, Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log _cat = LogFactory
			.getLog(JWebLiteFilterConfig.class);

	private final FilterConfig filterConfig;
	private final String attrPrefix;
	private final String encoding;
	private final boolean isGZipEnabled;
	private final String initClassName;
	private final String errorPage;

	public JWebLiteFilterConfig(FilterConfig filterConfig) {
		super();
		this.filterConfig = filterConfig;
		// parameters
		this.attrPrefix = StringUtils.getStringValue(
				(filterConfig != null ? filterConfig
						.getInitParameter("AttrPrefix") : null), "Jwl", true);
		this.encoding = StringUtils.getStringValue(
				(filterConfig != null ? filterConfig
						.getInitParameter("Encoding") : null), "UTF-8", true);
		this.isGZipEnabled = "true".equalsIgnoreCase(StringUtils
				.getStringValue(
						(filterConfig != null ? filterConfig
								.getInitParameter("GZipEnabled") : null),
						"true", true));
		this.initClassName = StringUtils.getStringValue(
				(filterConfig != null ? filterConfig
						.getInitParameter("InitClassName") : null), null, true);
		this.errorPage = StringUtils.getStringValue(
				(filterConfig != null ? filterConfig
						.getInitParameter("ErrorPage") : null), null, true);
		if (_cat.isInfoEnabled()) {
			_cat.info(this.toString());
		}
	}

	@Override
	public String toString() {
		return String
				.format("JWebLiteFilterConfig: { AttrPrefix: %s, Encoding: %s, GZipEnabled: %s, InitClassName: %s, ErrorPage: %s }",
						this.attrPrefix, this.encoding, this.isGZipEnabled,
						this.initClassName, this.errorPage);
	}

	public String getFilterName() {
		if (this.filterConfig == null) {
			return null;
		}
		return this.filterConfig.getFilterName();
	}

	public String getInitParameter(String name) {
		if (this.filterConfig == null) {
			return null;
		}
		return this.filterConfig.getInitParameter(name);
	}

	public Enumeration getInitParameterNames() {
		if (this.filterConfig == null) {
			return null;
		}
		return this.filterConfig.getInitParameterNames();
	}

	public ServletContext getServletContext() {
		if (this.filterConfig == null) {
			return null;
		}
		return this.filterConfig.getServletContext();
	}

	/**
	 * Get Attr Prefix
	 * 
	 * @return String
	 */
	public String getAttrPrefix() {
		return attrPrefix;
	}

	/**
	 * Get Encoding
	 * 
	 * @return String
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * Is GZip Enabled
	 * 
	 * @return boolean
	 */
	public boolean isGZipEnabled() {
		return isGZipEnabled;
	}

	/**
	 * Get Init Class Name
	 * 
	 * @return String
	 */
	public String getInitClassName() {
		return initClassName;
	}

	/**
	 * Get Error Page
	 * 
	 * @return String
	 */
	public String getErrorPage() {
		return errorPage;
	}

}