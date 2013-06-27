package jweblite.web.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class LineFilteredOutputStreamWriter extends OutputStreamWriter
		implements LineWriterListener {

	private int lineIndex = -1;
	private StringBuilder lineBuffer = null;
	private boolean isListenerEnabled = true;

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
		int end = 0;
		if (off < 0 || len < 0 || (end = off + len) < 0 || end > cbuf.length) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = off; i < end; i++) {
			write(cbuf[i]);
		}
	}

	@Override
	public void write(String str, int off, int len) throws IOException {
		write(str.substring(off, off + len).toCharArray(), 0, len);
	}

	@Override
	public void write(int c) throws IOException {
		// first line
		if (isListenerEnabled() && lineIndex < 0) {
			stopListener();
			onFirstLine(this);
			startListener();
		}
		if (isListenerEnabled()) {
			if (lineBuffer == null) {
				lineBuffer = new StringBuilder();
				lineIndex++;
			}
			lineBuffer.append((char) c);
			if (c == '\n') {
				writeNewLineBuffer();
			}
		} else {
			super.write(c);
		}
	}

	/**
	 * Is Listener Enabled
	 * 
	 * @return boolean
	 */
	public boolean isListenerEnabled() {
		return isListenerEnabled;
	}

	/**
	 * Start Listener
	 */
	public void startListener() {
		isListenerEnabled = true;
	}

	/**
	 * Stop Listener
	 */
	public void stopListener() {
		isListenerEnabled = false;
	}

	/**
	 * Write Newline Buffer
	 * 
	 * @throws IOException
	 */
	public void writeNewLineBuffer() throws IOException {
		// keep
		String line = lineBuffer.toString();
		lineBuffer = null;
		// before
		if (isListenerEnabled()) {
			stopListener();
			onBeforeLine(this, lineIndex, line);
			startListener();
		}
		super.write(line, 0, line.length());
		// after
		if (isListenerEnabled()) {
			stopListener();
			onAfterLine(this, lineIndex, line);
			startListener();
		}
	}

	@Override
	public void close() throws IOException {
		// flush
		if (isListenerEnabled() && lineBuffer != null) {
			writeNewLineBuffer();
		}
		if (isListenerEnabled()) {
			stopListener();
			onLastLine(this);
			startListener();
		}
		flush();
		super.close();
	}

	/**
	 * Get LineIndex
	 * 
	 * @return int
	 */
	public int getLineIndex() {
		return lineIndex;
	}

	public void onFirstLine(Writer writer) throws IOException {
	}

	public void onBeforeLine(Writer writer, int index, String line)
			throws IOException {
	}

	public void onAfterLine(Writer writer, int index, String line)
			throws IOException {
	}

	public void onLastLine(Writer writer) throws IOException {
	}

}