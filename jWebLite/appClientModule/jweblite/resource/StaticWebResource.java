package jweblite.resource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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

public abstract class StaticWebResource implements JWebLitePage, WebResource {
	private static final long serialVersionUID = 1L;
	private static final Log _cat = LogFactory.getLog(StaticWebResource.class);

	/**
	 * Default constructor.
	 */
	public StaticWebResource() {
		super();
	}

	public void doRequest(WebContext context, FormModel formModel)
			throws SkipException {
		try {
			// header
			doHeader(context, formModel);
			// body
			doBody(context, formModel);
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
		String contentType = getContentType();
		if (contentType != null) {
			resp.setContentType(contentType);
		}
		// encoding
		String encoding = getEncoding();
		if (encoding == null) {
			encoding = JWebLiteApplication.get().getFilterConfig()
					.getEncoding();
		}
		// fileName
		String fileName = getFileName();
		if (fileName != null
				&& (fileName = StringUtils.encodeDownloadFileName(req,
						fileName, encoding)) != null) {
			resp.setHeader("Content-Disposition",
					"attachment; filename=".concat(fileName));
		}
		// cacheable
		if (!isCacheable()) {
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);
		}
	}

	public void doBody(WebContext context, FormModel formModel)
			throws SkipException {
		// write
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new BufferedInputStream(new FileInputStream(loadData(context,
					formModel)));
			os = new BufferedOutputStream(context.getResponse()
					.getOutputStream());
			IOUtils.copy(is, os);
			os.flush();
		} catch (Exception e) {
			_cat.warn("Write data failed!", e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
	}

	public String getEncoding() {
		return null;
	}

	public boolean isCacheable() {
		return true;
	}

	/**
	 * Load Data
	 * 
	 * @param context
	 *            WebContext
	 * @param formModel
	 *            FormModel
	 * @return File
	 */
	public abstract File loadData(WebContext context, FormModel formModel);

}