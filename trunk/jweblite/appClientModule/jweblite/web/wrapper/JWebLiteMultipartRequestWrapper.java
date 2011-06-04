package jweblite.web.wrapper;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jweblite.data.MultiValueHashMap;
import jweblite.util.CollectionUtils;

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
	private final boolean isMultipart;
	private Map parametersMap = new MultiValueHashMap();
	private Map<String, FileItem> fileItemsMap = new HashMap();

	/**
	 * Default constructor.
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param repository
	 *            File
	 * @param maxSize
	 *            int
	 * @throws UnsupportedEncodingException
	 * @throws FileUploadException
	 */
	public JWebLiteMultipartRequestWrapper(JWebLiteRequestWrapper req,
			File repository, int maxSize) throws UnsupportedEncodingException,
			FileUploadException {
		super(req, req.getEncoding());
		// init
		this.repository = repository;
		this.maxSize = maxSize;
		String contentType = null;
		if (!this.isGetMethod()
				&& (contentType = req.getContentType()) != null
				&& (contentType = contentType.toLowerCase())
						.startsWith("multipart/")) {
			this.isMultipart = true;
		} else {
			this.isMultipart = false;
		}
		this.initialize(req, this.isMultipart);
	}

	/**
	 * Initialize
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param isMultipart
	 *            boolean
	 * @throws FileUploadException
	 * @throws UnsupportedEncodingException
	 */
	private void initialize(HttpServletRequest req, boolean isMultipart)
			throws FileUploadException, UnsupportedEncodingException {
		// copy parameters from parameters map
		for (Enumeration<String> e = req.getParameterNames(); e
				.hasMoreElements();) {
			String paramName = e.nextElement();
			String[] paramValueArray = super.getParameterValues(paramName);
			if (paramValueArray != null) {
				((MultiValueHashMap) this.parametersMap).putAll(paramName,
						Arrays.asList(paramValueArray));
			}
		}
		if (isMultipart) {
			// create a new file upload handler
			ServletFileUpload uploadHandler = this.createFileUploadHandler();
			// parse the request
			List<FileItem> items = uploadHandler.parseRequest(req);
			for (FileItem item : items) {
				String fieldName = item.getFieldName();
				if (fieldName == null) {
					continue;
				}
				if (item.isFormField()) {
					this.parametersMap.put(fieldName,
							item.getString(this.getEncoding()));
				} else {
					this.fileItemsMap.put(fieldName, item);
				}
			}
		}
		// unmodifiable
		this.parametersMap = Collections.unmodifiableMap(this.parametersMap);
		this.fileItemsMap = Collections.unmodifiableMap(this.fileItemsMap);
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
		uploadHandler.setHeaderEncoding(this.getEncoding());
		return uploadHandler;

	}

	@Override
	public boolean isMultipart() {
		return isMultipart;
	}

	@Override
	public String getParameter(String name) {
		List<String> valueList = (List) this.parametersMap.get(name);
		if (valueList == null) {
			return null;
		}
		return valueList.get(0);
	}

	@Override
	public String[] getParameterValues(String name) {
		List<String> valueList = (List) this.parametersMap.get(name);
		if (valueList == null) {
			return null;
		}
		return CollectionUtils.toArray(valueList, String.class);
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