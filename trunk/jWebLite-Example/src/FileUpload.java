import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.JWebLiteMultipartRequestWrapper;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

import org.apache.commons.fileupload.FileItem;

public class FileUpload implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private String test = null;
	private FileItem file = null;

	/**
	 * Default constructor.
	 */
	public FileUpload() {
		super();
	}

	public void doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) throws SkipException {
		// parse the request
		try {
			req = new JWebLiteMultipartRequestWrapper(req, null,
					10 * 1000 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.test = req.getHtmlParameter("test", "File");
		this.file = req.getFileParameter("file");
	}

	public String getTest() {
		return test;
	}

	public FileItem getFile() {
		return file;
	}

}