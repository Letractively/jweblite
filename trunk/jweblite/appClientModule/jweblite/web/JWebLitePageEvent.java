package jweblite.web;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import jweblite.web.wrapper.JWebLiteRequestWrapper;

public interface JWebLitePageEvent extends Serializable {

	/**
	 * Do Header
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            HttpServletResponse
	 * @throws SkipException
	 */
	public void doHeader(JWebLiteRequestWrapper req, HttpServletResponse resp)
			throws SkipException;

	/**
	 * Do Body
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            HttpServletResponse
	 * @throws SkipException
	 */
	public void doBody(JWebLiteRequestWrapper req, HttpServletResponse resp)
			throws SkipException;

	/**
	 * Do Finalize
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            HttpServletResponse
	 */
	public void doFinalize(JWebLiteRequestWrapper req, HttpServletResponse resp);

}