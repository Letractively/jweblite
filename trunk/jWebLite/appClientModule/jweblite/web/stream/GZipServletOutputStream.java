package jweblite.web.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;

public class GZipServletOutputStream extends ServletOutputStream {

	private final GZIPOutputStream outputStream;

	/**
	 * Default constructor.
	 */
	public GZipServletOutputStream(OutputStream outputStream)
			throws IOException {
		this.outputStream = new GZIPOutputStream(outputStream);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		this.outputStream.write(b, off, len);
	}

	@Override
	public void write(byte[] b) throws IOException {
		this.outputStream.write(b);
	}

	@Override
	public void write(int b) throws IOException {
		this.outputStream.write(b);
	}

	@Override
	public void flush() throws IOException {
		this.outputStream.flush();
	}

	@Override
	public void close() throws IOException {
		this.outputStream.close();
	}

}