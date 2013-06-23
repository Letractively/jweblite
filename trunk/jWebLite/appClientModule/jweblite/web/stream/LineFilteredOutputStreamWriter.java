package jweblite.web.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class LineFilteredOutputStreamWriter extends OutputStreamWriter {

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
	public LineFilteredOutputStreamWriter(OutputStream out, Charset cs) {
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
	public LineFilteredOutputStreamWriter(OutputStream out, CharsetEncoder enc) {
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
	public LineFilteredOutputStreamWriter(OutputStream out, String charsetName)
			throws UnsupportedEncodingException {
		super(out, charsetName);
	}

	/**
	 * Default constructor.
	 * 
	 * @param out
	 *            OutputStream
	 */
	public LineFilteredOutputStreamWriter(OutputStream out) {
		super(out);
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		if (cbuf == null) {
			return;
		}
		for (int i = off; i < off + len; i++) {
			write(cbuf[i]);
		}
	}

	@Override
	public void write(int c) throws IOException {
		if (lineBuffer == null) {
			lineBuffer = new StringBuilder();
			// trigger first line event
			if (lineIndex == 0) {
				doInit();
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
		doFinish();
		// flush
		if (lineBuffer != null) {
			writeNewLineBuffer();
		}
		flush();
		super.close();
	}

	/**
	 * Do Init
	 * 
	 * @throws IOException
	 */
	public void doInit() throws IOException {
	}

	/**
	 * Do Before Line
	 * 
	 * @param line
	 * @return String
	 * @throws IOException
	 */
	public String doBeforeLine(String line) throws IOException {
		return line;
	}

	/**
	 * Do After Line
	 * 
	 * @param line
	 * @throws IOException
	 */
	public void doAfterLine(String line) throws IOException {
	}

	/**
	 * Do Finish
	 * 
	 * @throws IOException
	 */
	public void doFinish() throws IOException {
	}

	/**
	 * Write Newline Buffer
	 * 
	 * @throws IOException
	 */
	private void writeNewLineBuffer() throws IOException {
		// keep the current line data
		String line = lineBuffer.toString();
		lineBuffer = null;
		line = doBeforeLine(line);
		if (line == null) {
			throw new NullPointerException(
					"The LineWriterListener cannot write the null pointer!");
		}
		if (lineBuffer != null) {
			lineBuffer.append(line);
			line = lineBuffer.toString();
			lineBuffer = null;
		}
		lineIndex++;
		super.write(line, 0, line.length());
		doAfterLine(line);
	}

	/**
	 * @return the lineIndex
	 */
	public int getLineIndex() {
		return lineIndex;
	}

}