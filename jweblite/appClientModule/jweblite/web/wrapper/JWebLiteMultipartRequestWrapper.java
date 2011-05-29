package jweblite.web.wrapper;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteMultipartRequestWrapper extends JWebLiteRequestWrapper {

	private Log log = LogFactory.getLog(this.getClass());

	private Map<String, String> parametersMap = Collections
			.unmodifiableMap(new HashMap());

	private Map<String, FileItem> fileItemsMap = Collections
			.unmodifiableMap(new HashMap());

	/**
	 * Default constructor.
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @throws FileUploadException
	 */
	public JWebLiteMultipartRequestWrapper(HttpServletRequest req)
			throws FileUploadException {
		super(req);
		this.initialize(req);
	}

	/**
	 * Default constructor.
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param encoding
	 *            String
	 * @throws UnsupportedEncodingException
	 * @throws FileUploadException
	 */
	public JWebLiteMultipartRequestWrapper(HttpServletRequest req,
			String encoding) throws UnsupportedEncodingException,
			FileUploadException {
		super(req, encoding);
		this.initialize(req);
	}

	/**
	 * Initialize
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @throws FileUploadException
	 */
	public void initialize(HttpServletRequest req) throws FileUploadException {
		// Create a new file upload handler
		ServletFileUpload uploadHandler = this.initFileUploadHandler();
		// Parse the request
		List<FileItem> items = uploadHandler.parseRequest(req);
		for (FileItem item : items) {
			String fieldName = item.getFieldName();
			if (item.isFormField()) {
				this.parametersMap.put(fieldName, item.getString());
			} else {
				this.fileItemsMap.put(fieldName, item);
			}
		}
	}

	/**
	 * Init File Upload Handler
	 * 
	 * @return ServletFileUpload
	 */
	public ServletFileUpload initFileUploadHandler() {
		return new ServletFileUpload(new DiskFileItemFactory());
	}

	@Override
	public boolean isMultipart() {
		return true;
	}

	@Override
	public String getParameter(String name) {
		return this.parametersMap.get(name);
	}

	@Override
	public String[] getParameterValues(String name) {
		String values = this.getParameter(name);
		if (values == null) {
			return null;
		}
		return values.split(",");
	}

	@Override
	public FileItem getFileParameter(String name) {
		return this.fileItemsMap.get(name);
	}

}