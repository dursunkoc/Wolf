/**
 * 
 */
package com.aric.esb.exceptions;

/**
 * @author Dursun KOC
 *
 */
public class NoServiceFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1998076614922963774L;

	/**
	 * @param message
	 */
	public NoServiceFoundException(String message) {
		super(message);
	}

}
