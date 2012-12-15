package jweblite.web;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.web.wrapper.FormModel;

public interface JWebLitePageEvent extends Serializable {

	/**
	 * Do Header
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param resp
	 *            HttpServletResponse
	 * @param formModel
	 *            FormModel
	 * @throws SkipException
	 */
	public void doHeader(HttpServletRequest req, HttpServletResponse resp,
			FormModel formModel) throws SkipException;

	/**
	 * Do Body
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param resp
	 *            HttpServletResponse
	 * @param formModel
	 *            FormModel
	 * @throws SkipException
	 */
	public void doBody(HttpServletRequest req, HttpServletResponse resp,
			FormModel formModel) throws SkipException;

	/**
	 * Do Finish
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param resp
	 *            HttpServletResponse
	 * @param formModel
	 *            FormModel
	 */
	public void doFinish(HttpServletRequest req, HttpServletResponse resp,
			FormModel formModel);

}