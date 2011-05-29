import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLitePage;
import jweblite.web.wrapper.JWebLiteMultipartRequestWrapper;
import jweblite.web.wrapper.JWebLiteRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

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

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp) {
		// parse the request
		try {
			req = new JWebLiteMultipartRequestWrapper(req, null,
					10 * 1000 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.test = req.getParameter("test", "File");
		this.file = req.getFileParameter("file");
		return false;
	}

	public String getTest() {
		return test;
	}

	public FileItem getFile() {
		return file;
	}

}