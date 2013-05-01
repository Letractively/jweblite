package jweblite.web.stream;

import java.io.IOException;
import java.io.Writer;

public interface LineWriterListener {

	/**
	 * Do Init
	 * 
	 * @param writer
	 *            Writer
	 * @param index
	 *            int
	 * @throws IOException
	 */
	public void doInit(Writer writer, int index) throws IOException;

	/**
	 * Do Before Line
	 * 
	 * @param writer
	 *            Writer
	 * @param line
	 *            String
	 * @param index
	 *            int
	 * @return String
	 * @throws IOException
	 */
	public String doBeforeLine(Writer writer, String line, int index)
			throws IOException;

	/**
	 * Do After Line
	 * 
	 * @param writer
	 *            Writer
	 * @param line
	 *            String
	 * @param index
	 *            int
	 * @throws IOException
	 */
	public void doAfterLine(Writer writer, String line, int index)
			throws IOException;

	/**
	 * Do Finish
	 * 
	 * @param writer
	 *            Writer
	 * @param index
	 *            int
	 * @throws IOException
	 */
	public void doFinish(Writer writer, int index) throws IOException;

}