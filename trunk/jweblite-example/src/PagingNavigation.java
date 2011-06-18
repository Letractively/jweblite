import javax.servlet.http.HttpServletResponse;

import jweblite.data.provider.CollectionDataProvider;
import jweblite.web.JWebLitePage;
import jweblite.web.wrapper.JWebLiteRequestWrapper;

public class PagingNavigation implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private String[] userNameArray = { "Anthony Bautista", "Lisa Drennan",
			"Adam Boggess", "Nora Velarde", "Michael Stites",
			"Jeannette Waits", "Jeff Rapp", "Nora Melvin", "Agnes Minnich",
			"Johnny Casarez" };
	private CollectionDataProvider<String> dataProvider = new CollectionDataProvider(
			userNameArray, 3);

	/**
	 * Default constructor.
	 */
	public PagingNavigation() {
		super();
	}

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			HttpServletResponse resp) {
		this.dataProvider.setCurrentIndex(req.getIntParameter("page", 0));
		return false;
	}

	public CollectionDataProvider<String> getDataProvider() {
		return dataProvider;
	}

}