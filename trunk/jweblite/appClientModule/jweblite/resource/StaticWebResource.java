package jweblite.resource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import jweblite.util.StringUtils;
import jweblite.web.JWebLitePage;
import jweblite.web.wrapper.JWebLiteRequestWrapper;

import org.apache.commons.io.IOUtils;

public abstract class StaticWebResource implements JWebLitePage, WebResource {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp) {
		String contentType = this.getContentType();
		if (contentType != null) {
			resp.setContentType(contentType);
		}
		String fileName = this.getFileName();
		if (fileName != null
				&& (fileName = StringUtils.encodeFileName(req, fileName,
						req.getEncoding())) != null) {
			resp.setHeader("Content-Disposition", "filename=".concat(fileName));
		}
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
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(bis);
			IOUtils.closeQuietly(bos);
		}
		return true;
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