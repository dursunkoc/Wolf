package com.aric.esb;

import com.aric.esb.arguments.Argument;

/**
 * @author Dursun KOC
 *
 */
public class ArgumentBundle {
	private Argument[] arguments;

	public ArgumentBundle() {
	}

	public void setArguments(Argument[] arguments) {
		this.arguments = arguments;
	}

	public ArgumentBundle(Argument[] arguments) {
		this.arguments = arguments;
	}

	public Object[] marshallArguments() {
		Object[] marshalledArgs = new Object[arguments.length];
		int iter = 0;
		for (Argument argument : arguments) {
			marshalledArgs[iter++] = argument.getValue();
		}
		return marshalledArgs;
	}

	@SuppressWarnings("rawtypes")
	public Class[] marshallArgumentClasses() {
		Class[] marshalledArgCls = new Class[arguments.length];
		int iter = 0;
		for (Argument argument : arguments) {
			marshalledArgCls[iter++] = argument.getRepresantClass();
		}
		return marshalledArgCls;
	}
	
	/**
	 * @return
	 */
	public int size(){
		return this.arguments.length;
	}

}
