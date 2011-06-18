package jweblite.resource;

import java.io.BufferedOutputStream;

import javax.servlet.http.HttpServletResponse;

import jweblite.util.StringUtils;
import jweblite.web.JWebLitePage;
import jweblite.web.JWebLitePageEvent;
import jweblite.web.SkipException;
import jweblite.web.wrapper.JWebLiteRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class DynamicWebResource implements JWebLitePage,
		JWebLitePageEvent, WebResource {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * Default constructor.
	 */
	public DynamicWebResource() {
		super();
	}

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp) {
		try {
			// header
			this.doHeader(req, resp);
			// body
			this.doBody(req, resp);
			// finalize
			this.doFinalize(req, resp);
		} catch (SkipException e) {
		}
		return true;
	}

	@Override
	public void doHeader(JWebLiteRequestWrapper req, HttpServletResponse resp)
			throws SkipException {
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
				&& (fileName = StringUtils.encodeFileName(req, fileName,
						encoding)) != null) {
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
	public void doBody(JWebLiteRequestWrapper req, HttpServletResponse resp)
			throws SkipException {
		// write
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(resp.getOutputStream());
			IOUtils.write(this.loadData(req), bos);
			bos.flush();
		} catch (Exception e) {
			log.warn("Write data failed!", e);
		} finally {
			IOUtils.closeQuietly(bos);
		}
	}

	@Override
	public void doFinalize(JWebLiteRequestWrapper req, HttpServletResponse resp) {
		// nothing
	}

	@Override
	public String getEncoding() {
		return null;
	}

	@Override
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
	public abstract byte[] loadData(JWebLiteRequestWrapper req);

}