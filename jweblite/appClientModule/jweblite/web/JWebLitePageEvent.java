package jweblite.web;

import java.io.Serializable;

import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

public interface JWebLitePageEvent extends Serializable {

	/**
	 * Do Header
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            JWebLiteResponseWrapper
	 * @throws SkipException
	 */
	public void doHeader(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) throws SkipException;

	/**
	 * Do Body
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            JWebLiteResponseWrapper
	 * @throws SkipException
	 */
	public void doBody(JWebLiteRequestWrapper req, JWebLiteResponseWrapper resp)
			throws SkipException;

	/**
	 * Do Finalize
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @param resp
	 *            JWebLiteResponseWrapper
	 */
	public void doFinalize(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp);

}