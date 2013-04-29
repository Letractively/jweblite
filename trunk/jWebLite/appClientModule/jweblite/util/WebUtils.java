package jweblite.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import jweblite.web.wrapper.JWebLiteResponseWrapper;
import jweblite.web.wrapper.stream.JWebLiteResponseWrapperStream;

public class WebUtils {

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
		JWebLiteResponseWrapperStream respWrapperStream = respWrapper
				.getWrapperStream();
		OutputStream originalOutputStream = respWrapperStream
				.getOriginalOutputStream();
		// proxy
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		respWrapperStream.setGZipEnabled(false);
		respWrapperStream.resetOutputStream(baos);
		req.getRequestDispatcher(servletPath).forward(req, resp);
		// revert
		respWrapperStream.setGZipEnabled(respWrapper.isGZipEnabled());
		respWrapperStream.resetOutputStream(originalOutputStream);
		return baos.toString();
	}

}