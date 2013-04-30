package jweblite.web.page;

import java.io.Serializable;

public interface JWebLitePage extends Serializable {

	/**
	 * Do Request
	 * 
	 * @param context
	 *            WebContext
	 * @param formModel
	 *            FormModel
	 * @throws SkipException
	 */
	public void doRequest(WebContext context, FormModel formModel)
			throws SkipException;

}