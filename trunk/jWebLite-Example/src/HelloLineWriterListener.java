import java.io.IOException;
import java.io.OutputStreamWriter;

import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;
import jweblite.web.stream.LineWriterListener;

public class HelloLineWriterListener implements JWebLitePage {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public HelloLineWriterListener() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
		context.bindLineWriterListener(new LineWriterListener() {
			public void doInit(OutputStreamWriter writer, int index)
					throws IOException {
			}

			public void doFinish(OutputStreamWriter writer, int index)
					throws IOException {
			}

			public String doBeforeLine(OutputStreamWriter writer, String line,
					int index) throws IOException {
				return line;
			}

			public void doAfterLine(OutputStreamWriter writer, String line,
					int index) throws IOException {
				if (line.contains("Hello LineWriterListener!")) {
					writer.write("<br />LineWriterListener: Hello Sir!");
				}
			}
		});
	}

}