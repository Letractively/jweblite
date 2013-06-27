package jweblite.web.stream;

import java.io.IOException;
import java.io.Writer;

public interface LineWriterListener {

	/**
	 * On First Line
	 * 
	 * @param writer
	 *            Writer
	 * @throws IOException
	 */
	public void onFirstLine(Writer writer) throws IOException;

	/**
	 * On Before Line
	 * 
	 * @param writer
	 *            Writer
	 * @param index
	 *            int
	 * @param line
	 *            String
	 * @throws IOException
	 */
	public void onBeforeLine(Writer writer, int index, String line)
			throws IOException;

	/**
	 * On After Line
	 * 
	 * @param writer
	 *            Writer
	 * @param index
	 *            int
	 * @param line
	 *            String
	 * @throws IOException
	 */
	public void onAfterLine(Writer writer, int index, String line)
			throws IOException;

	/**
	 * On Last Line
	 * 
	 * @param writer
	 *            Writer
	 * @throws IOException
	 */
	public void onLastLine(Writer writer) throws IOException;

}