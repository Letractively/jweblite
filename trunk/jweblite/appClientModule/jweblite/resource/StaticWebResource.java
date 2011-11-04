package jweblite.resource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import jweblite.util.StringUtils;
import jweblite.web.JWebLitePage;
import jweblite.web.JWebLitePageEvent;
import jweblite.web.SkipException;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class StaticWebResource implements JWebLitePage,
		JWebLitePageEvent, WebResource {

	private static final long serialVersionUID = 1L;
	private static final Log _cat = LogFactory.getLog(StaticWebResource.class);

	/**
	 * Default constructor.
	 */
	public StaticWebResource() {
		super();
	}

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) {
		try {
			if (this.isIgnoreGzip()) {
				resp.setGZipEnabled(false);
			}
			// header
			this.doHeader(req, resp);
			// body
			this.doBody(req, resp);
			// finalize
			this.doFinalize(req, resp);
		} catch (SkipException se) {
		} catch (Exception e) {
			_cat.warn("Write data failed!", e);
		}
		return true;
	}

	@Override
	public void doHeader(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) throws SkipException {
		// contentType
		String contentType = this.getContentType();
		if (contentType != null) {
			resp.setContentType(contentType);
		}
		// encoding
		String encoding = this.getEncoding();
		if (encoding == null) {
			encoding = req.getEncoding();
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

	@Override
	public void doBody(JWebLiteRequestWrapper req, JWebLiteResponseWrapper resp)
			throws SkipException {
		// write
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(
					this.loadData(req)));
			bos = new BufferedOutputStream(resp.getOutputStream());
			IOUtils.copy(bis, bos);
			bos.flush();
		} catch (Exception e) {
			_cat.warn("Write data failed!", e);
		} finally {
			IOUtils.closeQuietly(bis);
			IOUtils.closeQuietly(bos);
		}
	}

	@Override
	public void doFinalize(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) {
		// nothing
	}

	@Override
	public String getEncoding() {
		return null;
	}

	@Override
	public boolean isCacheable() {
		return true;
	}

	@Override
	public boolean isIgnoreGzip() {
		return false;
	}

	/**
	 * load Data
	 * 
	 * @param req
	 *            JWebLiteRequestWrapper
	 * @return File
	 */
	public abstract File loadData(JWebLiteRequestWrapper req);

}