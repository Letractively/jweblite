package jweblite.web.dispatcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JWebLiteRequestDispatcherTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRequestDispatcher() {
		JWebLiteRequestDispatcher dispatcher = new JWebLiteRequestDispatcher();
		System.out.println(dispatcher
				.getDispatchSettings("/redirect/RedirectTarget.jsp"));
	}

}