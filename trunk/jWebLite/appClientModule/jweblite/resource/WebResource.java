package jweblite.resource;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.web.page.FormModel;
import jweblite.web.page.SkipException;

public interface WebResource extends Serializable {

	/**
	 * Get Content Type
	 * 
	 * @return String
	 */
	public String getContentType();

	/**
	 * Get File Name
	 * 
	 * @return String
	 */
	public String getFileName();

	/**
	 * Get Encoding
	 * 
	 * @return String
	 */
	public String getEncoding();

	/**
	 * Is Cacheable
	 * 
	 * @return boolean
	 */
	public boolean isCacheable();

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

}