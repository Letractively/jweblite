package jweblite.web.dispatcher;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JWebLiteRequestDispatcherTest {

	private JWebLiteRequestDispatcher dispatcher = null;

	@Before
	public void setUp() throws Exception {
		this.dispatcher = new JWebLiteRequestDispatcher();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHomePath() {
		Assert.assertNull(this.dispatcher.dispatch("/"));
	}

	// ------------------------- illegal ------------------------------

	@Test
	public void testIllegal1() {
		Assert.assertNull(this.dispatcher.dispatch(null));
	}

	@Test
	public void testIllegal2() {
		Assert.assertNull(this.dispatcher.dispatch(""));
	}

	@Test
	public void testIllegal3() {
		Assert.assertNull(this.dispatcher.dispatch("."));
	}

	@Test
	public void testIllegal4() {
		Assert.assertNull(this.dispatcher.dispatch("/Test."));
	}

	@Test
	public void testIllegal5() {
		Assert.assertNull(this.dispatcher.dispatch("/Te.st."));
	}

	@Test
	public void testIllegal6() {
		Assert.assertNull(this.dispatcher.dispatch("/Te..st"));
	}

	@Test
	public void testIllegal7() {
		Assert.assertNull(this.dispatcher.dispatch("/./Test"));
	}

	// ------------------------- legal ------------------------------

	@Test
	public void testLegal1() {
		Assert.assertEquals("test", this.dispatcher.dispatch("/test.jsp"));
	}

	@Test
	public void testLegal2() {
		Assert.assertEquals("tEsT", this.dispatcher.dispatch("/tEsT.jsp"));
	}

	@Test
	public void testLegal3() {
		Assert.assertEquals(".Test", this.dispatcher.dispatch("//Test.jsp"));
	}

	@Test
	public void testLegal4() {
		Assert.assertEquals("test.Test",
				this.dispatcher.dispatch("/test/Test.jsp"));
	}

}