package com.aric.esb.exceptions;
/**
 * @author Dursun KOC
 *
 */
public class BindVariableDoesNotExists extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4381555966299622622L;

	private String varName;

	public BindVariableDoesNotExists(String message, String varName) {
		super(message);
		this.varName = varName;
	}

	public String getVarName() {
		return varName;
	}
}
