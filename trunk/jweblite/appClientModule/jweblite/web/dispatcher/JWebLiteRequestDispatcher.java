package jweblite.web.dispatcher;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import jweblite.util.StringUtils;

public class JWebLiteRequestDispatcher implements Serializable {

	private static final long serialVersionUID = 1L;

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
	 * Do Dispatch
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @return ServletRequestDispatchSettings
	 */
	public JWebLiteRequestDispatchSettings doDispatch(HttpServletRequest req) {
		String servletPath = req.getServletPath();
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
		}
		return new JWebLiteRequestDispatchSettings(req, result.toString(), null);
	}

}