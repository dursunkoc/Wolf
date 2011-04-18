package com.aric.esb.exceptions;
/**
 * @author Dursun KOC
 *
 */
public class DynamicCallerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5977519540166779164L;

	public DynamicCallerException(Exception e) {
		super(e);
	}

	public DynamicCallerException(String string) {
		super(string);
	}

}
