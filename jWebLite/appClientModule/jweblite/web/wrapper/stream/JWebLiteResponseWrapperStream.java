package jweblite.web.wrapper.stream;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletOutputStream;

public interface JWebLiteResponseWrapperStream extends Serializable {

	/**
	 * Get OutputStream
	 * 
	 * @param isGZipEnabled
	 *            boolean
	 * @return ServletOutputStream
	 * @throws IOException
	 */
	public ServletOutputStream getOutputStream(boolean isGZipEnabled)
			throws IOException;

	/**
	 * Get Writer
	 * 
	 * @param isGZipEnabled
	 *            boolean
	 * @param encoding
	 *            String
	 * @return PrintWriter
	 * @throws IOException
	 */
	public PrintWriter getWriter(boolean isGZipEnabled, String encoding)
			throws IOException;

	/**
	 * Do Finish
	 * 
	 * @param isGZipEnabled
	 *            boolean
	 */
	public void doFinish(boolean isGZipEnabled);

}