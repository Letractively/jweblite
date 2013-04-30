import jweblite.data.provider.CollectionDataProvider;
import jweblite.web.page.FormModel;
import jweblite.web.page.JWebLitePage;
import jweblite.web.page.SkipException;
import jweblite.web.page.WebContext;

public class PagingNavigation implements JWebLitePage {
	private static final long serialVersionUID = 1L;

	private CollectionDataProvider<String> dataProvider = new CollectionDataProvider<String>(
			new String[] { "Anthony Bautista", "Lisa Drennan", "Adam Boggess",
					"Nora Velarde", "Michael Stites", "Jeannette Waits",
					"Jeff Rapp", "Nora Melvin", "Agnes Minnich",
					"Johnny Casarez" }, 3);

	/**
	 * Default constructor.
	 */
	public PagingNavigation() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm)
			throws SkipException {
		this.dataProvider.setCurrentIndex(fm.getInt("page", 0));
	}

	public CollectionDataProvider<String> getDataProvider() {
		return dataProvider;
	}

}