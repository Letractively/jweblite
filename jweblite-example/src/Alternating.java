import javax.servlet.http.HttpServletResponse;

import jweblite.util.iterator.LoopIterator;
import jweblite.web.JWebLitePage;
import jweblite.web.wrapper.JWebLiteRequestWrapper;

public class Alternating implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private LoopIterator<String> colorIterator = new LoopIterator(new String[] {
			"red", "green", "blue" });

	/**
	 * Default constructor.
	 */
	public Alternating() {
		super();
	}

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp) {
		return false;
	}

	public LoopIterator<String> getColorIterator() {
		return colorIterator;
	}

}