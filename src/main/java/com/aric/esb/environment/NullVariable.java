package com.aric.esb.environment;
/**
 * @author Dursun KOC
 *
 */
public class NullVariable implements Variable {
	private NullVariable() {
	}
	
	public static NullVariable getInstance(){
		return new NullVariable();
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public boolean isNull() {
		return true;
	}
	
	@Override
	public String toString() {
		return null;
	}

}
