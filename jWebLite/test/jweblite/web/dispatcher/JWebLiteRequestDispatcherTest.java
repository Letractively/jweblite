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
		dispatcher = new JWebLiteRequestDispatcher();
	}

	public void testIllegalPath() throws Exception {
		Assert.assertNull(dispatcher.dispatch("/"));
		Assert.assertNull(dispatcher.dispatch("//"));
		Assert.assertNull(dispatcher.dispatch(null));
		Assert.assertNull(dispatcher.dispatch(""));
		Assert.assertNull(dispatcher.dispatch(" "));
		Assert.assertNull(dispatcher.dispatch("."));
		Assert.assertNull(dispatcher.dispatch("/Test."));
		Assert.assertNull(dispatcher.dispatch("/Te.st."));
		Assert.assertNull(dispatcher.dispatch("/Te..st"));
		Assert.assertNull(dispatcher.dispatch("/./Test"));
		Assert.assertNull(dispatcher.dispatch("/ /Test"));
		Assert.assertNull(dispatcher.dispatch("/^/Test"));
	}

	public void testLegalPath() throws Exception {
		Assert.assertEquals("test", dispatcher.dispatch("/test.jsp"));
		Assert.assertEquals("tEsT", dispatcher.dispatch("/tEsT.jsp"));
		Assert.assertEquals(".Test", dispatcher.dispatch("//Test.jsp"));
		Assert.assertEquals("test.Test", dispatcher.dispatch("/test/Test.jsp"));
	}

}