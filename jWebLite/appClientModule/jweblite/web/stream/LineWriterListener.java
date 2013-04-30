package jweblite.web.stream;

import java.io.IOException;
import java.io.OutputStreamWriter;

public interface LineWriterListener {

	/**
	 * Do Init
	 * 
	 * @param osw
	 *            OutputStreamWriter
	 * @throws IOException
	 */
	public void doInit(OutputStreamWriter osw) throws IOException;

	/**
	 * Do Before Line
	 * 
	 * @param osw
	 *            OutputStreamWriter
	 * @param line
	 *            String
	 * @return String
	 * @throws IOException
	 */
	public String doBeforeLine(OutputStreamWriter osw, String line)
			throws IOException;

	/**
	 * Do After Line
	 * 
	 * @param osw
	 *            OutputStreamWriter
	 * @param line
	 *            String
	 * @throws IOException
	 */
	public void doAfterLine(OutputStreamWriter osw, String line)
			throws IOException;

	/**
	 * Do Finish
	 * 
	 * @param osw
	 *            OutputStreamWriter
	 * @throws IOException
	 */
	public void doFinish(OutputStreamWriter osw) throws IOException;

}