package jweblite.web.wrapper.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletOutputStream;

import jweblite.web.stream.LineWriterListener;

public interface JWebLiteResponseWrapperStream extends Serializable {

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
	 * Do Finish
	 */
	public void doFinish();

	/**
	 * Get Original OutputStream
	 * 
	 * @return OutputStream
	 */
	public OutputStream getOriginalOutputStream();

	/**
	 * Reset OutputStream
	 * 
	 * @param os
	 *            OutputStream
	 */
	public void resetOutputStream(OutputStream os);

	/**
	 * Bind LineWriterListener
	 * 
	 * @param lineWriterListener
	 *            LineWriterListener
	 */
	public void bindLineWriterListener(LineWriterListener lineWriterListener);

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

}