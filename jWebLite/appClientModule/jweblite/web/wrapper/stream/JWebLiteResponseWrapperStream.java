package jweblite.web.wrapper.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletOutputStream;

import jweblite.web.stream.LineFilteredOutputStreamEvent;

public interface JWebLiteResponseWrapperStream extends Serializable {

	/**
	 * Get Servlet OutputStream
	 * 
	 * @return ServletOutputStream
	 * @throws IOException
	 */
	public ServletOutputStream getServletOutputStream() throws IOException;

	/**
	 * Get Writer
	 * 
	 * @return PrintWriter
	 * @throws IOException
	 */
	public PrintWriter getWriter() throws IOException;

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
	 * Set LineFilteredOutputStreamEvent
	 * 
	 * @param lineFilteredOutputStreamEvent
	 *            LineFilteredOutputStreamEvent
	 */
	public void setLineFilteredOutputStreamEvent(
			LineFilteredOutputStreamEvent lineFilteredOutputStreamEvent);

}