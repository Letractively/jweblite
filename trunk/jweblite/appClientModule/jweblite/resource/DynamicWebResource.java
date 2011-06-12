package jweblite.resource;

import java.io.BufferedOutputStream;

import javax.servlet.http.HttpServletResponse;

import jweblite.util.StringUtils;
import jweblite.web.JWebLitePage;
import jweblite.web.wrapper.JWebLiteRequestWrapper;

import org.apache.commons.io.IOUtils;

public abstract class DynamicWebResource implements JWebLitePage, WebResource {

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
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(resp.getOutputStream());
			IOUtils.write(this.loadData(req), bos);
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(bos);
		}
		return true;
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