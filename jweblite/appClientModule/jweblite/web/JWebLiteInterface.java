package jweblite.web;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

public interface JWebLiteInterface extends Serializable {

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