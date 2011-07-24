package jweblite.web.dispatcher;

import java.io.Serializable;

import jweblite.util.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteRequestDispatcher implements Serializable {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private int urlPathPadding;

	/**
	 * Default constructor.
	 */
	public JWebLiteRequestDispatcher(int urlPathPadding) {
		super();
		this.urlPathPadding = urlPathPadding;
	}

	/**
	 * Default constructor.
	 */
	public JWebLiteRequestDispatcher() {
		this(0);
	}

	/**
	 * Get Dispatch Settings
	 * 
	 * @param servletPath
	 *            String
	 * @param isInclude
	 *            boolean
	 * @return JWebLiteRequestDispatchSettings
	 */
	public JWebLiteRequestDispatchSettings getDispatchSettings(
			String servletPath) {
		String currentUrl = servletPath.substring(StringUtils.indexOf(
				servletPath, "/", this.urlPathPadding) + 1);
		int urlLength = -1;
		int lastUrlCommaIndex = -1;
		if ((urlLength = currentUrl.length()) == 0
				|| (lastUrlCommaIndex = currentUrl.lastIndexOf(".")) != currentUrl
						.indexOf(".")) {
			return null;
		}
		StringBuffer result = new StringBuffer();
		try {
			// replace all '/' to '.'
			String resultClassName = currentUrl.substring(0,
					(lastUrlCommaIndex >= 0 ? lastUrlCommaIndex : urlLength))
					.replace("/", ".");
			if (resultClassName.length() > 0) {
				// package name
				int resultClassNamePackageIndex = resultClassName
						.lastIndexOf(".") + 1;
				if (resultClassNamePackageIndex > 0) {
					result.append(resultClassName.substring(0,
							resultClassNamePackageIndex));
				}
				// class name
				result.append(resultClassName
						.substring(resultClassNamePackageIndex));
			}
		} catch (Exception e) {
			this.log.warn("Get dispatch settings failed!", e);
		}
		return new JWebLiteRequestDispatchSettings(servletPath,
				result.toString(), currentUrl);
	}

}