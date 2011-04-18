package com.aric.esb.exceptions;
/**
 * @author Dursun KOC
 *
 */
public class CallerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5977519540166779164L;

	public CallerException(Exception e) {
		super(e);
	}

	public CallerException(String string) {
		super(string);
	}

}
