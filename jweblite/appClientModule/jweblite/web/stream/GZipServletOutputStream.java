package jweblite.web.stream;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;

public class GZipServletOutputStream extends ServletOutputStream {

	private final GZIPOutputStream gos;

	/**
	 * Default constructor.
	 */
	public GZipServletOutputStream(ServletOutputStream out) throws IOException {
		this.gos = new GZIPOutputStream(out);
	}

	@Override
	public void close() throws IOException {
		this.gos.close();
	}

	@Override
	public void flush() throws IOException {
		this.gos.flush();
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		this.gos.write(b, off, len);
	}

	@Override
	public void write(byte[] b) throws IOException {
		this.gos.write(b);
	}

	@Override
	public void write(int b) throws IOException {
		this.gos.write(b);
	}

}