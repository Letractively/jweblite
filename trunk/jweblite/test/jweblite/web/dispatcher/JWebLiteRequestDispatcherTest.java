package jweblite.web.dispatcher;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JWebLiteRequestDispatcherTest {

	private JWebLiteRequestDispatcher dispatcher = null;
	private JWebLiteRequestDispatcher paddingDispatcher = null;

	@Before
	public void setUp() throws Exception {
		this.dispatcher = new JWebLiteRequestDispatcher();
		this.paddingDispatcher = new JWebLiteRequestDispatcher(2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHomePath() {
		Assert.assertNull(this.dispatcher.getDispatchSettings("/"));
	}

	// ------------------------- illegal ------------------------------

	@Test
	public void testIllegal1() {
		Assert.assertNull(this.dispatcher.getDispatchSettings(null));
	}

	@Test
	public void testIllegal2() {
		Assert.assertNull(this.dispatcher.getDispatchSettings(""));
	}

	@Test
	public void testIllegal3() {
		Assert.assertNull(this.dispatcher.getDispatchSettings("."));
	}

	@Test
	public void testIllegal4() {
		Assert.assertNull(this.dispatcher.getDispatchSettings("/Test."));
	}

	@Test
	public void testIllegal5() {
		Assert.assertNull(this.dispatcher.getDispatchSettings("/Te.st."));
	}

	// ------------------------- legal ------------------------------

	@Test
	public void testLegal1() {
		JWebLiteRequestDispatchSettings reqDispatchSettings = this.dispatcher
				.getDispatchSettings("/test.jsp");
		Assert.assertEquals("/test.jsp",
				reqDispatchSettings.getReferenceResourcePath());
		Assert.assertEquals("test", reqDispatchSettings.getReferenceClassName());
	}

	@Test
	public void testLegal2() {
		JWebLiteRequestDispatchSettings reqDispatchSettings = this.dispatcher
				.getDispatchSettings("/tEsT.jsp");
		Assert.assertEquals("/tEsT.jsp",
				reqDispatchSettings.getReferenceResourcePath());
		Assert.assertEquals("tEsT", reqDispatchSettings.getReferenceClassName());
	}

	@Test
	public void testLegal3() {
		JWebLiteRequestDispatchSettings reqDispatchSettings = this.dispatcher
				.getDispatchSettings("//Test.jsp");
		Assert.assertEquals("//Test.jsp",
				reqDispatchSettings.getReferenceResourcePath());
		Assert.assertEquals(".Test",
				reqDispatchSettings.getReferenceClassName());
	}

	@Test
	public void testLegal4() {
		JWebLiteRequestDispatchSettings reqDispatchSettings = this.dispatcher
				.getDispatchSettings("/test/Test.jsp");
		Assert.assertEquals("/test/Test.jsp",
				reqDispatchSettings.getReferenceResourcePath());
		Assert.assertEquals("test.Test",
				reqDispatchSettings.getReferenceClassName());
	}

	// ------------------------- padding ------------------------------

	@Test
	public void testPadding1() {
		JWebLiteRequestDispatchSettings reqDispatchSettings = this.paddingDispatcher
				.getDispatchSettings("/test/test/Test.jsp");
		Assert.assertEquals("/Test.jsp",
				reqDispatchSettings.getReferenceResourcePath());
		Assert.assertEquals("Test", reqDispatchSettings.getReferenceClassName());
	}

	@Test
	public void testPadding2() {
		Assert.assertNull(this.paddingDispatcher
				.getDispatchSettings("/Test.jsp"));
	}

	@Test
	public void testPadding3() {
		JWebLiteRequestDispatchSettings reqDispatchSettings = this.paddingDispatcher
				.getDispatchSettings("/test/test/test/Test.jsp");
		Assert.assertEquals("/test/Test.jsp",
				reqDispatchSettings.getReferenceResourcePath());
		Assert.assertEquals("test.Test",
				reqDispatchSettings.getReferenceClassName());
	}

}