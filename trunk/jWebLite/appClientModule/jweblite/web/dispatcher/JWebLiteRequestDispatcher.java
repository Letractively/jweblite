package jweblite.web.dispatcher;

import java.io.Serializable;

public class JWebLiteRequestDispatcher implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public JWebLiteRequestDispatcher() {
		super();
	}

	/**
	 * Dispatch
	 * 
	 * @param servletPath
	 *            String
	 * @return String
	 */
	public String dispatch(String servletPath) {
		if (!this.isValidClassPath(servletPath)) {
			return null;
		}
		int lastUrlCommaIndex = servletPath.lastIndexOf('.');
		// replace all '/' to '.'
		String refClassName = servletPath.substring(
				1,
				(lastUrlCommaIndex >= 0 ? lastUrlCommaIndex : servletPath
						.length())).replace('/', '.');
		return refClassName;
	}

	/**
	 * Is Valid Class Path
	 * 
	 * @param servletPath
	 *            String
	 * @return boolean
	 */
	public boolean isValidClassPath(String servletPath) {
		int urlLength = -1;
		if (servletPath == null || !servletPath.startsWith("/")
				|| (urlLength = servletPath.length()) <= 1) {
			return false;
		}
		// path pattern check
		if (!servletPath.matches("[\\/\\w\\$\\.]+")) {
			return false;
		}
		// empty path pattern check
		int lastUrlSlashIndex = servletPath.lastIndexOf('/');
		if (lastUrlSlashIndex == urlLength - 1) {
			return false;
		}
		// path pattern check (.)
		int lastUrlCommaIndex = servletPath.lastIndexOf('.');
		if (lastUrlCommaIndex == urlLength - 1
				|| lastUrlCommaIndex < lastUrlSlashIndex
				|| lastUrlCommaIndex != servletPath.indexOf('.')) {
			return false;
		}
		return true;
	}

}