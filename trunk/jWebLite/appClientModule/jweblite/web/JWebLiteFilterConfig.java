package jweblite.web;

import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import jweblite.util.LogUtils;
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
	private final long fileUploadSizeMax;

	public JWebLiteFilterConfig(FilterConfig filterConfig) {
		super();
		this.filterConfig = filterConfig;
		// parameters
		attrPrefix = StringUtils.getStringValue(
				(filterConfig != null ? filterConfig
						.getInitParameter("AttrPrefix") : null), "Jwl", true);
		encoding = StringUtils.getStringValue(
				(filterConfig != null ? filterConfig
						.getInitParameter("Encoding") : null), "UTF-8", true);
		isGZipEnabled = "true"
				.equalsIgnoreCase(StringUtils.getStringValue(
						(filterConfig != null ? filterConfig
								.getInitParameter("GZipEnabled") : null),
						"true", true));
		initClassName = StringUtils.getStringValue(
				(filterConfig != null ? filterConfig
						.getInitParameter("InitClassName") : null), null, true);
		errorPage = StringUtils.getStringValue(
				(filterConfig != null ? filterConfig
						.getInitParameter("ErrorPage") : null), null, true);
		fileUploadSizeMax = StringUtils.getLongValue(
				(filterConfig != null ? filterConfig
						.getInitParameter("FileUploadSizeMax") : null),
				10 * 1024 * 1024);
		if (_cat.isInfoEnabled()) {
			_cat.info(toString());
		}
	}

	@Override
	public String toString() {
		String infoFormat = "AttrPrefix=%s, Encoding=%s, GZipEnabled=%s, InitClassName=%s, ErrorPage=%s";
		return LogUtils.formatDebugLog("JWebLiteFilterConfig", infoFormat,
				attrPrefix, encoding, isGZipEnabled, initClassName, errorPage);
	}

	public String getFilterName() {
		if (filterConfig == null) {
			return null;
		}
		return filterConfig.getFilterName();
	}

	public String getInitParameter(String name) {
		if (filterConfig == null) {
			return null;
		}
		return filterConfig.getInitParameter(name);
	}

	public Enumeration<?> getInitParameterNames() {
		if (filterConfig == null) {
			return null;
		}
		return filterConfig.getInitParameterNames();
	}

	public ServletContext getServletContext() {
		if (filterConfig == null) {
			return null;
		}
		return filterConfig.getServletContext();
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

	/**
	 * Get File Upload Size Max
	 * 
	 * @return long
	 */
	public long getFileUploadSizeMax() {
		return fileUploadSizeMax;
	}

}