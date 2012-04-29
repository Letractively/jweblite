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

	private int urlPathPadding = 0;

	/**
	 * Default constructor.
	 */
	public JWebLiteRequestDispatcher(int urlPathPadding) {
		super();
		if (urlPathPadding > 0) {
			this.urlPathPadding = urlPathPadding;
		}
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
		if (servletPath == null || !servletPath.startsWith("/")
				|| servletPath.endsWith(".")) {
			return null;
		}
		// padding
		int startIndex = StringUtils.indexOf(servletPath, "/",
				this.urlPathPadding);
		if (startIndex < 0) {
			return null;
		}
		String currentUrl = servletPath.substring(startIndex);
		// path pattern check
		int urlLength = -1;
		int lastUrlCommaIndex = -1;
		if ((urlLength = currentUrl.length()) <= 1
				|| (lastUrlCommaIndex = currentUrl.lastIndexOf('.')) != currentUrl
						.indexOf('.')) {
			return null;
		}
		// replace all '/' to '.'
		String refClassName = currentUrl.substring(1,
				(lastUrlCommaIndex >= 0 ? lastUrlCommaIndex : urlLength))
				.replace("/", ".");
		return new JWebLiteRequestDispatchSettings(servletPath, refClassName,
				currentUrl);
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