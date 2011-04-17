package jweblite.web;

import javax.servlet.http.HttpServletResponse;

public interface JWebLiteInterface {

	/**
	 * Do Request
	 * 
	 * @param request
	 *            JWebLiteRequestWrapper
	 * @param response
	 *            HttpServletResponse
	 */
	public void doRequest(JWebLiteRequestWrapper request,
			HttpServletResponse response);

}