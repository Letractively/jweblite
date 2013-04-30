package jweblite.resource;

import java.io.Serializable;

import jweblite.web.page.FormModel;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

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
	 * @param context
	 *            WebContext
	 * @param formModel
	 *            FormModel
	 * @throws SkipException
	 */
	public void doHeader(WebContext context, FormModel formModel)
			throws SkipException;

	/**
	 * Do Body
	 * 
	 * @param context
	 *            WebContext
	 * @param formModel
	 *            FormModel
	 * @throws SkipException
	 */
	public void doBody(WebContext context, FormModel formModel)
			throws SkipException;

}