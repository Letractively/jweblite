package jweblite.web.stream;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

public class ProxyServletOutputStream extends ServletOutputStream {

	private final OutputStream outputStream;

	/**
	 * Default constructor.
	 */
	public ProxyServletOutputStream(OutputStream outputStream)
			throws IOException {
		this.outputStream = outputStream;
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