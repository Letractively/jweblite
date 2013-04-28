package jweblite.web.stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.swingui.TestRunner;

public class LineFilteredOutputStreamWriterTest extends TestCase {

	private ByteArrayOutputStream baos = null;

	public static void main(String[] args) {
		TestRunner.run(LineFilteredOutputStreamWriterTest.class);
	}

	public void setUp() throws Exception {
		baos = new ByteArrayOutputStream();
	}

	public void testUnmodify() throws Exception {
		PrintWriter pw = new PrintWriter(new LineFilteredOutputStreamWriter(
				baos, "UTF-8"));
		pw.print("Content1\nContent2\nContent3");
		pw.close();
		Assert.assertEquals("Content1\nContent2\nContent3",
				new String(baos.toByteArray()));
	}

	public void testModifyTopAndBottom() throws Exception {
		PrintWriter pw = new PrintWriter(new LineFilteredOutputStreamWriter(
				baos, "UTF-8") {
			@Override
			public void doInit() throws IOException {
				super.doInit();
				write("before1\nbefore2");
			}

			@Override
			public void doFinish() throws IOException {
				super.doFinish();
				write("after1\nafter2");
			}
		});
		pw.print("Content1\nContent2\nContent3");
		pw.close();
		Assert.assertEquals(
				"before1\nbefore2Content1\nContent2\nContent3after1\nafter2",
				new String(baos.toByteArray()));
	}

	public void testModifyLine() throws Exception {
		PrintWriter pw = new PrintWriter(new LineFilteredOutputStreamWriter(
				baos, "UTF-8") {
			@Override
			public void doBeforeRenderLine(String line) throws IOException {
				super.doBeforeRenderLine(line);
				if (line.contains("Content2")) {
					write("before1\nbefore2");
				}
			}

			@Override
			public void doAfterRenderLine(String line) throws IOException {
				super.doAfterRenderLine(line);
				if (line.contains("Content2")) {
					write("after1\nafter2");
				}
			}
		});
		pw.print("Content1\nContent2\nContent3");
		pw.close();
		Assert.assertEquals(
				"Content1\nbefore1\nbefore2Content2\nafter1\nafter2Content3",
				new String(baos.toByteArray()));
	}

}