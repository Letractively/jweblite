package jweblite.resource;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.util.StringUtils;
import jweblite.web.JWebLiteApplication;
import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class DynamicWebResource implements JWebLitePage, WebResource {
	private static final long serialVersionUID = 1L;
	private static final Log _cat = LogFactory.getLog(DynamicWebResource.class);

	/**
	 * Default constructor.
	 */
	public DynamicWebResource() {
		super();
	}

	public void doRequest(WebContext context, FormModel formModel)
			throws SkipException {
		try {
			// header
			this.doHeader(context, formModel);
			// body
			this.doBody(context, formModel);
		} catch (SkipException se) {
			throw se;
		} catch (Exception e) {
			_cat.warn("Write data failed!", e);
		}
		throw new SkipException();
	}

	public void doHeader(WebContext context, FormModel formModel)
			throws SkipException {
		HttpServletRequest req = context.getRequest();
		HttpServletResponse resp = context.getResponse();
		// contentType
		String contentType = this.getContentType();
		if (contentType != null) {
			resp.setContentType(contentType);
		}
		// encoding
		String encoding = this.getEncoding();
		if (encoding == null) {
			encoding = JWebLiteApplication.get().getFilterConfig()
					.getEncoding();
		}
		// fileName
		String fileName = this.getFileName();
		if (fileName != null
				&& (fileName = StringUtils.encodeDownloadFileName(req,
						fileName, encoding)) != null) {
			resp.setHeader("Content-Disposition", "filename=".concat(fileName));
		}
		// cacheable
		if (!this.isCacheable()) {
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);
		}
	}

	public void doBody(WebContext context, FormModel formModel)
			throws SkipException {
		// write
		OutputStream os = null;
		try {
			os = new BufferedOutputStream(context.getResponse()
					.getOutputStream());
			IOUtils.write(this.loadData(context, formModel), os);
			os.flush();
		} catch (Exception e) {
			_cat.warn("Write data failed!", e);
		} finally {
			IOUtils.closeQuietly(os);
		}
	}

	public String getEncoding() {
		return null;
	}

	public boolean isCacheable() {
		return false;
	}

	/**
	 * Load Data
	 * 
	 * @param context
	 *            WebContext
	 * @param formModel
	 *            FormModel
	 * @return byte[]
	 */
	public abstract byte[] loadData(WebContext context, FormModel formModel);

}