/**
 * 
 */
package com.aric.esb.exceptions;

/**
 * @author Dursun KOC
 *
 */
public class GenericRunTimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7993938859082498190L;

	/**
	 * 
	 */
	public GenericRunTimeException() {
		super();
	}

	/**
	 * @param message
	 */
	public GenericRunTimeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public GenericRunTimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public GenericRunTimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
