package jweblite.web.wrapper.stream;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletOutputStream;

import jweblite.web.stream.LineWriterListener;

public interface JWebLiteResponseWrapperStream extends Serializable, Closeable {

	/**
	 * Get Servlet OutputStream
	 * 
	 * @return ServletOutputStream
	 * @throws IOException
	 */
	public ServletOutputStream getServletOutputStream() throws IOException;

	/**
	 * Get Servlet Writer
	 * 
	 * @return PrintWriter
	 * @throws IOException
	 */
	public PrintWriter getServletWriter() throws IOException;

	/**
	 * Is GZip Enabled
	 * 
	 * @return boolean
	 */
	public boolean isGZipEnabled();

	/**
	 * Set GZip Enabled
	 * 
	 * @param isGZipEnabled
	 *            boolean
	 */
	public void setGZipEnabled(boolean isGZipEnabled);

	/**
	 * Get Original OutputStream
	 * 
	 * @return OutputStream
	 */
	public OutputStream getOriginalOutputStream();

	/**
	 * Reset OutputStream
	 * 
	 * @param outputStream
	 *            OutputStream
	 */
	public void resetOutputStream(OutputStream outputStream);

	/**
	 * Get LineWriterListener
	 * 
	 * @return LineWriterListener
	 */
	public LineWriterListener getLineWriterListener();

	/**
	 * Set LineWriterListener
	 * 
	 * @param lineWriterListener
	 *            LineWriterListener
	 */
	public void setLineWriterListener(LineWriterListener lineWriterListener);

}