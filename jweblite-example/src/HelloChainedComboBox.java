import jweblite.data.MultiValueLinkedHashMap;
import jweblite.data.MultiValueMap;
import jweblite.web.JWebLitePage;
import jweblite.web.wrapper.JWebLiteRequestWrapper;
import jweblite.web.wrapper.JWebLiteResponseWrapper;

public class HelloChainedComboBox implements JWebLitePage {

	private static final long serialVersionUID = 1L;

	private MultiValueMap map1 = null;
	private MultiValueMap map2 = null;

	/**
	 * Default constructor.
	 */
	public HelloChainedComboBox() {
		super();
		// map1
		this.map1 = new MultiValueLinkedHashMap();
		this.map1.put("Choose one...", null);
		this.map1.put("color", "red");
		this.map1.put("color", "green");
		this.map1.put("color", "black");
		this.map1.put("car", "AUDI");
		this.map1.put("car", "BMW");
		this.map1.put("car", "FORD");
		// map2
		this.map2 = new MultiValueLinkedHashMap();
		for (Object map1ValueObj : this.map1.values()) {
			if (map1ValueObj == null) {
				continue;
			}
			String map1Value = (String) map1ValueObj;
			char[] map2ValueArray = map1Value.toCharArray();
			for (char c : map2ValueArray) {
				this.map2.put(map1Value, c);
			}
		}
	}

	@Override
	public boolean doRequest(JWebLiteRequestWrapper req,
			JWebLiteResponseWrapper resp) {
		return false;
	}

	public MultiValueMap getMap1() {
		return map1;
	}

	public MultiValueMap getMap2() {
		return map2;
	}

}