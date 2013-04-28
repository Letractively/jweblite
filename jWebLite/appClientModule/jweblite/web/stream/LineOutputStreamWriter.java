package jweblite.web.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class LineOutputStreamWriter extends OutputStreamWriter {

	private int lineIndex = 0;
	private StringBuilder lineBuffer = null;

	/**
	 * Default constructor.
	 * 
	 * @param out
	 *            OutputStream
	 * @param cs
	 *            Charset
	 */
	public LineOutputStreamWriter(OutputStream out, Charset cs) {
		super(out, cs);
	}

	/**
	 * Default constructor.
	 * 
	 * @param out
	 *            OutputStream
	 * @param enc
	 *            CharsetEncoder
	 */
	public LineOutputStreamWriter(OutputStream out, CharsetEncoder enc) {
		super(out, enc);
	}

	/**
	 * Default constructor.
	 * 
	 * @param out
	 *            OutputStream
	 * @param charsetName
	 *            String
	 * @throws UnsupportedEncodingException
	 */
	public LineOutputStreamWriter(OutputStream out, String charsetName)
			throws UnsupportedEncodingException {
		super(out, charsetName);
	}

	/**
	 * Default constructor.
	 * 
	 * @param out
	 *            OutputStream
	 */
	public LineOutputStreamWriter(OutputStream out) {
		super(out);
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		if (cbuf == null) {
			return;
		}
		for (int i = off; i < off + len; i++) {
			write((int) cbuf[i]);
		}
	}

	@Override
	public void write(int c) throws IOException {
		if (lineBuffer == null) {
			lineBuffer = new StringBuilder();
			// trigger first line event
			if (lineIndex == 0) {
				beforeRenderFirstLine();
			}
		}
		lineBuffer.append((char) c);
		if (c == '\n') {
			writeNewLineBuffer();
		}
	}

	@Override
	public void write(String str, int off, int len) throws IOException {
		if (str == null) {
			return;
		}
		write(str.substring(off, off + len).toCharArray(), 0, len);
	}

	@Override
	public void close() throws IOException {
		afterRenderLatestLine();
		// flush
		if (lineBuffer != null) {
			writeNewLineBuffer();
		}
		flush();
		super.close();
	}

	/**
	 * Write Line Buffer
	 * 
	 * @throws IOException
	 */
	private void writeNewLineBuffer() throws IOException {
		lineIndex++;
		String line = lineBuffer.toString();
		lineBuffer = null;
		super.write(line, 0, line.length());
		onRenderLine(line);
	}

	/**
	 * Before Render First Line
	 * 
	 * @throws IOException
	 */
	public void beforeRenderFirstLine() throws IOException {
	}

	/**
	 * On Render Line
	 * 
	 * @param line
	 *            String
	 * @throws IOException
	 */
	public void onRenderLine(String line) throws IOException {
	}

	/**
	 * After Render Latest Line
	 * 
	 * @throws IOException
	 */
	public void afterRenderLatestLine() throws IOException {
	}

	/**
	 * @return the lineIndex
	 */
	public int getLineIndex() {
		return lineIndex;
	}

}