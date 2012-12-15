package jweblite.web;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.web.wrapper.FormModel;

public interface JWebLitePage extends Serializable {

	/**
	 * Do Request
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param resp
	 *            HttpServletResponse
	 * @param formModel
	 *            FormModel
	 * @throws SkipException
	 */
	public void doRequest(HttpServletRequest req, HttpServletResponse resp,
			FormModel formModel) throws SkipException;

}