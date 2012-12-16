package jweblite.resource;

import java.io.BufferedOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.util.StringUtils;
import jweblite.web.JWebLitePage;
import jweblite.web.JWebLitePageEvent;
import jweblite.web.SkipException;
import jweblite.web.application.JWebLiteApplication;
import jweblite.web.wrapper.FormModel;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class DynamicWebResource implements JWebLitePage,
		JWebLitePageEvent, WebResource {
	private static final long serialVersionUID = 1L;
	private static final Log _cat = LogFactory.getLog(DynamicWebResource.class);

	/**
	 * Default constructor.
	 */
	public DynamicWebResource() {
		super();
	}

	public void doRequest(HttpServletRequest req, HttpServletResponse resp,
			FormModel formModel) throws SkipException {
		try {
			// header
			this.doHeader(req, resp, formModel);
			// body
			this.doBody(req, resp, formModel);
			// finish
			this.doFinish(req, resp, formModel);
		} catch (SkipException se) {
			throw se;
		} catch (Exception e) {
			_cat.warn("Write data failed!", e);
		}
		throw new SkipException();
	}

	public void doHeader(HttpServletRequest req, HttpServletResponse resp,
			FormModel formModel) throws SkipException {
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
		if (this.isCacheable()) {
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);
		}
	}

	public void doBody(HttpServletRequest req, HttpServletResponse resp,
			FormModel formModel) throws SkipException {
		// write
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(resp.getOutputStream());
			IOUtils.write(this.loadData(req, formModel), bos);
			bos.flush();
		} catch (Exception e) {
			_cat.warn("Write data failed!", e);
		} finally {
			IOUtils.closeQuietly(bos);
		}
	}

	public void doFinish(HttpServletRequest req, HttpServletResponse resp,
			FormModel formModel) {
		// nothing
	}

	public String getEncoding() {
		return null;
	}

	public boolean isCacheable() {
		return false;
	}

	/**
	 * load Data
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @return byte[]
	 */
	public abstract byte[] loadData(HttpServletRequest req, FormModel formModel);

}