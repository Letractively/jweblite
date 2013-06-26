package jweblite.web.dispatcher;

import junit.framework.TestCase;
import junit.textui.TestRunner;

public class JWebLiteRequestDispatcherTest extends TestCase {

	private JWebLiteRequestDispatcher dispatcher = null;

	public static void main(String[] args) {
		TestRunner.run(JWebLiteRequestDispatcherTest.class);
	}

	public void setUp() throws Exception {
		dispatcher = new JWebLiteRequestDispatcher();
	}

	public void testIllegalPath() throws Exception {
		assertNull(dispatcher.dispatch("/"));
		assertNull(dispatcher.dispatch("//"));
		assertNull(dispatcher.dispatch(null));
		assertNull(dispatcher.dispatch(""));
		assertNull(dispatcher.dispatch(" "));
		assertNull(dispatcher.dispatch("."));
		assertNull(dispatcher.dispatch("/Test."));
		assertNull(dispatcher.dispatch("/Te.st."));
		assertNull(dispatcher.dispatch("/Te..st"));
		assertNull(dispatcher.dispatch("/./Test"));
		assertNull(dispatcher.dispatch("/ /Test"));
		assertNull(dispatcher.dispatch("/^/Test"));
	}

	public void testLegalPath() throws Exception {
		assertEquals("test", dispatcher.dispatch("/test.jsp"));
		assertEquals("tEsT", dispatcher.dispatch("/tEsT.jsp"));
		assertEquals(".Test", dispatcher.dispatch("//Test.jsp"));
		assertEquals("test.Test", dispatcher.dispatch("/test/Test.jsp"));
	}

}