package jweblite.web.wrapper;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWebLiteMultipartRequestWrapper extends JWebLiteRequestWrapper {

	private Log log = LogFactory.getLog(this.getClass());

	private final File repository;
	private final int maxSize;
	private Map<String, String> parametersMap = Collections
			.unmodifiableMap(new HashMap());
	private Map<String, FileItem> fileItemsMap = Collections
			.unmodifiableMap(new HashMap());

	/**
	 * Default constructor.
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param repository
	 *            File
	 * @param maxSize
	 *            int
	 * @throws FileUploadException
	 */
	public JWebLiteMultipartRequestWrapper(HttpServletRequest req,
			File repository, int maxSize) throws FileUploadException {
		super(req);
		// init
		this.repository = repository;
		this.maxSize = maxSize;
		this.initialize(req);
	}

	/**
	 * Default constructor.
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param encoding
	 *            String
	 * @param repository
	 *            File
	 * @param maxSize
	 *            int
	 * @throws UnsupportedEncodingException
	 * @throws FileUploadException
	 */
	public JWebLiteMultipartRequestWrapper(HttpServletRequest req,
			String encoding, File repository, int maxSize)
			throws UnsupportedEncodingException, FileUploadException {
		super(req, encoding);
		// init
		this.repository = repository;
		this.maxSize = maxSize;
		this.initialize(req);
	}

	/**
	 * Initialize
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @throws FileUploadException
	 */
	private void initialize(HttpServletRequest req) throws FileUploadException {
		// create a new file upload handler
		ServletFileUpload uploadHandler = this.createFileUploadHandler();
		// parse the request
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
	public FileItemFactory createFileItemFactory() {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);
		if (this.repository != null) {
			factory.setRepository(this.repository);
		}
		return factory;
	}

	/**
	 * Create File Upload Handler
	 * 
	 * @return ServletFileUpload
	 */
	public ServletFileUpload createFileUploadHandler() {
		ServletFileUpload uploadHandler = new ServletFileUpload(
				this.createFileItemFactory());
		uploadHandler.setSizeMax(this.maxSize);
		return uploadHandler;

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

	/**
	 * Get Repository
	 * 
	 * @return File
	 */
	public File getRepository() {
		return repository;
	}

	/**
	 * Get Max Size
	 * 
	 * @return int
	 */
	public int getMaxSize() {
		return maxSize;
	}

}