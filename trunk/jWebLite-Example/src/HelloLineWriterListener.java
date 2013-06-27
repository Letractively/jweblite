import java.io.IOException;
import java.io.Writer;

import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;
import jweblite.web.stream.LineWriterListener;

public class HelloLineWriterListener implements JWebLitePage,
		LineWriterListener {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public HelloLineWriterListener() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
	}

	public void onAfterLine(Writer writer, int index, String line)
			throws IOException {
		if (line.contains("Hello LineWriterListener!")) {
			writer.write("<br />LineWriterListener: Hello Sir!");
		}
	}

	public void onBeforeLine(Writer writer, int index, String line)
			throws IOException {
	}

	public void onFirstLine(Writer writer) throws IOException {
	}

	public void onLastLine(Writer writer) throws IOException {
	}

}