package com.aric.esb.environment;

/**
 * @author Dursun KOC
 * 
 */
public class FullVariable implements Variable {
	private Object value;

	private FullVariable(Object value) {
		this.value = value;
	}
	
	public static FullVariable getNewInstance(Object value){
		return new FullVariable(value);
	}

	@Override
	public Object getValue() {
		return this.value;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
