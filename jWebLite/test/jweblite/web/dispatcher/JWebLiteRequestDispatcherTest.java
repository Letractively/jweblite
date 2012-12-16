package jweblite.web.dispatcher;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.swingui.TestRunner;

public class JWebLiteRequestDispatcherTest extends TestCase {

	private JWebLiteRequestDispatcher dispatcher = null;

	public static void main(String[] args) {
		TestRunner.run(JWebLiteRequestDispatcherTest.class);
	}

	public void setUp() throws Exception {
		this.dispatcher = new JWebLiteRequestDispatcher();
	}

	public void tearDown() throws Exception {
	}

	public void testHomePath() {
		Assert.assertNull(this.dispatcher.dispatch("/"));
	}

	// ------------------------- illegal ------------------------------

	public void testIllegal1() {
		Assert.assertNull(this.dispatcher.dispatch(null));
	}

	public void testIllegal2() {
		Assert.assertNull(this.dispatcher.dispatch(""));
	}

	public void testIllegal3() {
		Assert.assertNull(this.dispatcher.dispatch("."));
	}

	public void testIllegal4() {
		Assert.assertNull(this.dispatcher.dispatch("/Test."));
	}

	public void testIllegal5() {
		Assert.assertNull(this.dispatcher.dispatch("/Te.st."));
	}

	public void testIllegal6() {
		Assert.assertNull(this.dispatcher.dispatch("/Te..st"));
	}

	public void testIllegal7() {
		Assert.assertNull(this.dispatcher.dispatch("/./Test"));
	}

	// ------------------------- legal ------------------------------

	public void testLegal1() {
		Assert.assertEquals("test", this.dispatcher.dispatch("/test.jsp"));
	}

	public void testLegal2() {
		Assert.assertEquals("tEsT", this.dispatcher.dispatch("/tEsT.jsp"));
	}

	public void testLegal3() {
		Assert.assertEquals(".Test", this.dispatcher.dispatch("//Test.jsp"));
	}

	public void testLegal4() {
		Assert.assertEquals("test.Test",
				this.dispatcher.dispatch("/test/Test.jsp"));
	}

}