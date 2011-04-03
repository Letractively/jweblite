package jweblite.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface JWebLiteInterface {

	/**
	 * Do Request
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response);

}