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

	public void testIllegalPath() throws Exception {
		Assert.assertNull(this.dispatcher.dispatch("/"));
		Assert.assertNull(this.dispatcher.dispatch("//"));
		Assert.assertNull(this.dispatcher.dispatch(null));
		Assert.assertNull(this.dispatcher.dispatch(""));
		Assert.assertNull(this.dispatcher.dispatch(" "));
		Assert.assertNull(this.dispatcher.dispatch("."));
		Assert.assertNull(this.dispatcher.dispatch("/Test."));
		Assert.assertNull(this.dispatcher.dispatch("/Te.st."));
		Assert.assertNull(this.dispatcher.dispatch("/Te..st"));
		Assert.assertNull(this.dispatcher.dispatch("/./Test"));
		Assert.assertNull(this.dispatcher.dispatch("/ /Test"));
		Assert.assertNull(this.dispatcher.dispatch("/^/Test"));
	}

	public void testLegalPath() throws Exception {
		Assert.assertEquals("test", this.dispatcher.dispatch("/test.jsp"));
		Assert.assertEquals("tEsT", this.dispatcher.dispatch("/tEsT.jsp"));
		Assert.assertEquals(".Test", this.dispatcher.dispatch("//Test.jsp"));
		Assert.assertEquals("test.Test",
				this.dispatcher.dispatch("/test/Test.jsp"));
	}

}