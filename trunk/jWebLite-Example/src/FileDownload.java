import jweblite.resource.DynamicWebResource;
import jweblite.web.page.FormModel;
import jweblite.web.page.WebContext;

public class FileDownload extends DynamicWebResource {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public FileDownload() {
		super();
	}

	public String getContentType() {
		return "unknown/unknown";
	}

	public String getFileName() {
		return "test.txt";
	}

	@Override
	public byte[] loadData(WebContext context, FormModel fm) {
		return new byte[1024];
	}

}