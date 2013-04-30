import java.io.BufferedWriter;

import javax.servlet.http.HttpServletResponse;

import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

import org.apache.commons.io.IOUtils;

public class HelloServlet implements JWebLitePage {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public HelloServlet() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
		HttpServletResponse resp = context.getResponse();
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