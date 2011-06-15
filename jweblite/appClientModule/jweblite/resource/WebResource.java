package jweblite.resource;

import java.io.Serializable;

public interface WebResource extends Serializable {

	/**
	 * Get Content Type
	 * 
	 * @return String
	 */
	public String getContentType();

	/**
	 * Get File Name
	 * 
	 * @return String
	 */
	public String getFileName();

	/**
	 * Get Encoding
	 * 
	 * @return String
	 */
	public String getEncoding();

}