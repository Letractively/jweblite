import jweblite.data.provider.CollectionDataProvider;
import jweblite.web.JWebLitePage;
import jweblite.web.SkipException;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

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

	@Override
	public void doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) throws SkipException {
		this.dataProvider.setCurrentIndex(req.getIntParameter("page", 0));
	}

	public CollectionDataProvider<String> getDataProvider() {
		return dataProvider;
	}

}