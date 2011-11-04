package jweblite.web.dispatcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;

import jweblite.util.StringUtils;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;
import jweblite.web.wrapper.stream.JWebLiteProxyResponseWrapperStream;
import jweblite.web.wrapper.stream.JWebLiteResponseWrapperStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteRequestDispatcher implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log _cat = LogFactory
			.getLog(JWebLiteRequestDispatcher.class);

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
	 * @return JWebLiteRequestDispatchSettings
	 */
	public JWebLiteRequestDispatchSettings getDispatchSettings(
			String servletPath) {
		if (servletPath == null) {
			return null;
		}
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
			_cat.warn("Get dispatch settings failed!", e);
		}
		return new JWebLiteRequestDispatchSettings(servletPath,
				result.toString(), currentUrl);
	}

	/**
	 * Write Page As String
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            JWebLiteResponseWrapper
	 * @param servletPath
	 *            String
	 * @return String
	 * @throws IOException
	 * @throws ServletException
	 */
	public String writePageAsString(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp, String servletPath)
			throws ServletException, IOException {
		if (servletPath == null) {
			return "";
		}
		// original wrapper stream
		JWebLiteResponseWrapperStream oriWrapperStream = resp
				.getWrapperStream();
		// proxy
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		resp.setWrapperStream(new JWebLiteProxyResponseWrapperStream(baos));
		req.getRequestDispatcher(servletPath).forward(req, resp);
		// revert
		resp.setWrapperStream(oriWrapperStream);
		return baos.toString();
	}

}