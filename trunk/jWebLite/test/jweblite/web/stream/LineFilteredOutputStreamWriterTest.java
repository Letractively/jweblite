package jweblite.web.stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import junit.framework.TestCase;
import junit.textui.TestRunner;

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
		assertEquals("Content1\nContent2\nContent3",
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
		assertEquals(
				"before1\nbefore2Content1\nContent2\nContent3after1\nafter2",
				new String(baos.toByteArray()));
	}

	public void testModifyLine() throws Exception {
		PrintWriter pw = new PrintWriter(new LineFilteredOutputStreamWriter(
				baos, "UTF-8") {
			@Override
			public String doBeforeLine(String line) throws IOException {
				line = super.doBeforeLine(line);
				if (line.contains("Content2")) {
					write("before1\nbefore2");
					return "Content222\n";
				}
				return line;
			}

			@Override
			public void doAfterLine(String line) throws IOException {
				super.doAfterLine(line);
				if (line.contains("Content2")) {
					write("after1\nafter2");
				}
			}
		});
		pw.print("Content1\nContent2\nContent3");
		pw.close();
		assertEquals(
				"Content1\nbefore1\nbefore2Content222\nafter1\nafter2Content3",
				new String(baos.toByteArray()));
	}

}