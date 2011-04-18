/**
 * 
 */
package com.aric.esb.exceptions;

/**
 * @author Dursun KOC
 * 
 */
public class ConfigurationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7723658613638902392L;

	/**
	 * @param message
	 */
	public ConfigurationException(String message) {
		super(message);
	}

	/**
	 * @param e
	 */
	public ConfigurationException(Exception e) {
		super(e);
	}

}
