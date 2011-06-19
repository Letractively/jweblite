package jweblite.web.application;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import jweblite.web.dispatcher.JWebLiteRequestDispatchSettings;
import jweblite.web.wrapper.JWebLiteRequestWrapper;

public interface JWebLiteApplicationListener extends Serializable {

	/**
	 * Do Before Request
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            HttpServletResponse
	 * @param reqDispatchSettings
	 *            JWebLiteRequestDispatchSettings
	 */
	public void doBeforeRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp,
			JWebLiteRequestDispatchSettings reqDispatchSettings);

	/**
	 * Do Before Render
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            HttpServletResponse
	 */
	public void doBeforeRender(JWebLiteRequestWrapper req,
			HttpServletResponse resp);

	/**
	 * Do After Request
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            HttpServletResponse
	 */
	public void doAfterRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp);

	/**
	 * Destroy
	 */
	public void destroy();

}