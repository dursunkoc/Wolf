package com.aric.esb.exceptions;
/**
 * @author Dursun KOC
 *
 */
public class ResourceFactoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 116050072558023148L;

	public ResourceFactoryException() {
		super();
	}

	public ResourceFactoryException(String message) {
		super(message);
	}

	public ResourceFactoryException(Throwable cause) {
		super(cause);
	}

	public ResourceFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

}
