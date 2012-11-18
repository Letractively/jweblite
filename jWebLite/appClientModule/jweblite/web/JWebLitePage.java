package jweblite.web;

import java.io.Serializable;

import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

public interface JWebLitePage extends Serializable {

	/**
	 * Do Request
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            JWebLiteResponseWrapper
	 * @throws SkipException
	 */
	public void doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) throws SkipException;

}