package jweblite.web;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import jweblite.web.wrapper.JWebLiteRequestWrapper;

public interface JWebLitePage extends Serializable {

	/**
	 * Do Request
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            HttpServletResponse
	 * @return boolean
	 */
	public boolean doRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp);

}