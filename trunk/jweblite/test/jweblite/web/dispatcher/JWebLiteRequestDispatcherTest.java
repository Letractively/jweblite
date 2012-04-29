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
	public void testRequestDispatcher1() {
		Assert.assertNull(this.dispatcher.getDispatchSettings(null));
	}

	@Test
	public void testRequestDispatcher2() {
		Assert.assertNull(this.dispatcher.getDispatchSettings(""));
	}

	@Test
	public void testRequestDispatcher3() {
		Assert.assertNull(this.dispatcher.getDispatchSettings("/"));
	}

	@Test
	public void testRequestDispatcher4() {
		Assert.assertNull(this.dispatcher.getDispatchSettings("."));
	}

	@Test
	public void testRequestDispatcher5() {
		JWebLiteRequestDispatchSettings reqDispatchSettings = this.dispatcher
				.getDispatchSettings("/Test.jsp");
		Assert.assertEquals("/Test.jsp",
				reqDispatchSettings.getReferenceResourcePath());
		Assert.assertEquals("Test", reqDispatchSettings.getReferenceClassName());
	}

	@Test
	public void testRequestDispatcher6() {
		Assert.assertNull(this.dispatcher.getDispatchSettings("/Test."));
	}

	@Test
	public void testRequestDispatcher7() {
		Assert.assertNull(this.dispatcher.getDispatchSettings("/Te.st."));
	}

	@Test
	public void testRequestDispatcher8() {
		JWebLiteRequestDispatchSettings reqDispatchSettings = this.dispatcher
				.getDispatchSettings("//Test.jsp");
		Assert.assertEquals("//Test.jsp",
				reqDispatchSettings.getReferenceResourcePath());
		Assert.assertEquals(".Test",
				reqDispatchSettings.getReferenceClassName());
	}

	@Test
	public void testRequestDispatcher9() {
		JWebLiteRequestDispatchSettings reqDispatchSettings = this.dispatcher
				.getDispatchSettings("/test/Test.jsp");
		Assert.assertEquals("/test/Test.jsp",
				reqDispatchSettings.getReferenceResourcePath());
		Assert.assertEquals("test.Test",
				reqDispatchSettings.getReferenceClassName());
	}

	@Test
	public void testRequestDispatcher10() {
		JWebLiteRequestDispatchSettings reqDispatchSettings = this.paddingDispatcher
				.getDispatchSettings("/test/test/Test.jsp");
		Assert.assertEquals("/Test.jsp",
				reqDispatchSettings.getReferenceResourcePath());
		Assert.assertEquals("Test", reqDispatchSettings.getReferenceClassName());
	}

	@Test
	public void testRequestDispatcher11() {
		Assert.assertNull(this.paddingDispatcher
				.getDispatchSettings("/Test.jsp"));
	}

	@Test
	public void testRequestDispatcher12() {
		JWebLiteRequestDispatchSettings reqDispatchSettings = this.paddingDispatcher
				.getDispatchSettings("/test/test/test/Test.jsp");
		Assert.assertEquals("/test/Test.jsp",
				reqDispatchSettings.getReferenceResourcePath());
		Assert.assertEquals("test.Test",
				reqDispatchSettings.getReferenceClassName());
	}

}