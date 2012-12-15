import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.FormModel;

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

	public void doRequest(HttpServletRequest req, HttpServletResponse resp,
			FormModel formModel) throws SkipException {
		this.test = formModel.getEscapedString("test", "File");
		this.file = formModel.getFile("file");
	}

	public String getTest() {
		return test;
	}

	public FileItem getFile() {
		return file;
	}

}