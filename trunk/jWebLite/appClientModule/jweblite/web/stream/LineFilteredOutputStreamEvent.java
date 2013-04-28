package jweblite.web.stream;

import java.io.IOException;
import java.io.OutputStreamWriter;

public interface LineFilteredOutputStreamEvent {

	/**
	 * Do Init
	 * 
	 * @param osw
	 *            OutputStreamWriter
	 * @throws IOException
	 */
	public void doInit(OutputStreamWriter osw) throws IOException;

	/**
	 * Do Before Render Line
	 * 
	 * @param osw
	 *            OutputStreamWriter
	 * @param line
	 *            String
	 * @throws IOException
	 */
	public void doBeforeRenderLine(OutputStreamWriter osw, String line)
			throws IOException;

	/**
	 * Do After Render Line
	 * 
	 * @param osw
	 *            OutputStreamWriter
	 * @param line
	 *            String
	 * @throws IOException
	 */
	public void doAfterRenderLine(OutputStreamWriter osw, String line)
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