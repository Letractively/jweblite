package jweblite.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import jweblite.web.wrapper.JWebLiteResponseWrapper;
import jweblite.web.wrapper.stream.JWebLiteProxyResponseWrapperStream;
import jweblite.web.wrapper.stream.JWebLiteResponseWrapperStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WebUtils {

	private static final Log _cat = LogFactory.getLog(WebUtils.class);

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
	public static String writePageAsString(ServletRequest req,
			ServletResponse resp, String servletPath) throws ServletException,
			IOException {
		if (servletPath == null) {
			return "";
		}
		// original wrapper stream
		JWebLiteResponseWrapper respWrapper = (JWebLiteResponseWrapper) resp;
		JWebLiteResponseWrapperStream oriWrapperStream = respWrapper
				.getWrapperStream();
		// proxy
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		respWrapper.setWrapperStream(new JWebLiteProxyResponseWrapperStream(
				baos));
		req.getRequestDispatcher(servletPath).forward(req, resp);
		// revert
		respWrapper.setWrapperStream(oriWrapperStream);
		return baos.toString();
	}

}