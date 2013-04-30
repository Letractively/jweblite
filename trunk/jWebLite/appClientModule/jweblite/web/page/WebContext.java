package jweblite.web.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jweblite.web.stream.LineWriterListener;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

public class WebContext {

	private HttpServletRequest request = null;
	private HttpServletResponse response = null;

	/**
	 * Default constructor.
	 */
	public WebContext() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param resp
	 *            HttpServletResponse
	 */
	public WebContext(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	/**
	 * Bind LineWriterListener
	 * 
	 * @param lineWriterListener
	 *            LineWriterListener
	 */
	public void bindLineWriterListener(LineWriterListener lineWriterListener) {
		if (response == null || !(response instanceof JWebLiteResponseWrapper)) {
			throw new IllegalStateException();
		}
		JWebLiteResponseWrapper respWrapper = (JWebLiteResponseWrapper) response;
		respWrapper.getWrapperStream().bindLineWriterListener(
				lineWriterListener);
	}

	/**
	 * Get Session
	 * 
	 * @return HttpSession
	 */
	public HttpSession getSession() {
		if (request == null) {
			throw new IllegalStateException();
		}
		return request.getSession(true);
	}

	/**
	 * Is Session Existed
	 * 
	 * @return boolean
	 */
	public boolean isSessionExisted() {
		if (request == null) {
			throw new IllegalStateException();
		}
		return (request.getSession(false) != null);
	}

	/**
	 * Get Request
	 * 
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * Get Response
	 * 
	 * @return HttpServletResponse
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

}