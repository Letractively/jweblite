import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

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

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
		this.test = fm.getEscapedString("test", "File");
		this.file = fm.getFile("file");
	}

	public String getTest() {
		return test;
	}

	public FileItem getFile() {
		return file;
	}

}