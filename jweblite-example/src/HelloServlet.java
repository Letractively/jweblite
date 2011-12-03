import java.io.BufferedWriter;

import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

import org.apache.commons.io.IOUtils;

public class HelloServlet implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public HelloServlet() {
		super();
	}

	@Override
	public void doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) throws SkipException {
		resp.setContentType("text/html; charset=UTF-8");
		// write
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(resp.getWriter());
			bw.write("Hello Servlet!");
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(bw);
		}
		throw new SkipException();
	}

}