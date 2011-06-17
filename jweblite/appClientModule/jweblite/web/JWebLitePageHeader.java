package jweblite.web;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import jweblite.web.wrapper.JWebLiteRequestWrapper;

public interface JWebLitePageHeader extends Serializable {

	/**
	 * Do Header
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            HttpServletResponse
	 */
	public void doHeader(JWebLiteRequestWrapper req, HttpServletResponse resp);

}