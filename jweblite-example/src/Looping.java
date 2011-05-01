import javax.servlet.http.HttpServletResponse;

import jweblite.util.LoopIterator;
import jweblite.web.JWebLitePage;
import jweblite.web.JWebLiteRequestWrapper;

public class Looping implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private LoopIterator<String> colorIterator = new LoopIterator(new String[] {
			"red", "green", "blue" });

	/**
	 * Default constructor.
	 */
	public Looping() {
		super();
	}

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp) {
		return true;
	}

	public String getNextColor() {
		return colorIterator.next();
	}

}