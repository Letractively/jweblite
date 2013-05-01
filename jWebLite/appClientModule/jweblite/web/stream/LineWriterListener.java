package jweblite.web.stream;

import java.io.IOException;
import java.io.OutputStreamWriter;

public interface LineWriterListener {

	/**
	 * Do Init
	 * 
	 * @param osw
	 *            OutputStreamWriter
	 * @param index
	 *            int
	 * @throws IOException
	 */
	public void doInit(OutputStreamWriter osw, int index) throws IOException;

	/**
	 * Do Before Line
	 * 
	 * @param osw
	 *            OutputStreamWriter
	 * @param line
	 *            String
	 * @param index
	 *            int
	 * @return String
	 * @throws IOException
	 */
	public String doBeforeLine(OutputStreamWriter osw, String line, int index)
			throws IOException;

	/**
	 * Do After Line
	 * 
	 * @param osw
	 *            OutputStreamWriter
	 * @param line
	 *            String
	 * @param index
	 *            int
	 * @throws IOException
	 */
	public void doAfterLine(OutputStreamWriter osw, String line, int index)
			throws IOException;

	/**
	 * Do Finish
	 * 
	 * @param osw
	 *            OutputStreamWriter
	 * @param index
	 *            int
	 * @throws IOException
	 */
	public void doFinish(OutputStreamWriter osw, int index) throws IOException;

}