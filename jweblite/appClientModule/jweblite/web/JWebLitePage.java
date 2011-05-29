package jweblite.web;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import jweblite.web.wrapper.JWebLiteRequestWrapper;

public interface JWebLitePage extends Serializable {

	/**
	 * Do Request
	 * 
	 * @param request
	 *            JWebLiteRequestWrapper
	 * @param response
	 *            HttpServletResponse
	 * @return boolean
	 */
	public boolean doRequest(JWebLiteRequestWrapper request,
			HttpServletResponse response);

}