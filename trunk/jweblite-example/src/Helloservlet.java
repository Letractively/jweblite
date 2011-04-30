import java.io.BufferedWriter;

import javax.servlet.http.HttpServletResponse;

import jweblite.web.JWebLitePage;
import jweblite.web.JWebLiteRequestWrapper;

public class Helloservlet implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Helloservlet() {
		super();
	}

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp) {
		resp.setContentType("text/html");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Connection", "Keep-Alive");
		resp.setCharacterEncoding("UTF-8");
		// write
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(resp.getWriter());
			bw.write("Hello Servlet!");
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (Exception e2) {
				}
			}
		}
		return false;
	}

}