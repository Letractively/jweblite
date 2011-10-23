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
	private Log log = LogFactory.getLog(this.getClass());

	private final FilterConfig filterConfig;
	private final String attrPrefix;
	private final String encoding;
	private final int urlPathPadding;
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
		this.urlPathPadding = StringUtils.getIntValue(
				(filterConfig != null ? filterConfig
						.getInitParameter("UrlPathPadding") : null), 0);
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
		if (this.log.isInfoEnabled()) {
			this.log.info(this.toString());
		}
	}

	@Override
	public String toString() {
		return String
				.format("%s [ AttrPrefix: %s, Encoding: %s, UrlPathPadding: %s, GZipEnabled: %s, InitClassName: %s, ErrorPage: %s ]",
						this.getClass().getSimpleName(), this.attrPrefix,
						this.encoding, this.urlPathPadding, this.isGZipEnabled,
						this.initClassName, this.errorPage);
	}

	@Override
	public String getFilterName() {
		if (this.filterConfig == null) {
			return null;
		}
		return this.filterConfig.getFilterName();
	}

	@Override
	public String getInitParameter(String name) {
		if (this.filterConfig == null) {
			return null;
		}
		return this.filterConfig.getInitParameter(name);
	}

	@Override
	public Enumeration getInitParameterNames() {
		if (this.filterConfig == null) {
			return null;
		}
		return this.filterConfig.getInitParameterNames();
	}

	@Override
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
	 * Get Url Path Padding
	 * 
	 * @return int
	 */
	public int getUrlPathPadding() {
		return urlPathPadding;
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