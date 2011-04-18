package com.aric.esb.arguments;

import com.aric.esb.EType;
/**
 * @author Dursun KOC
 *
 */
public class PrimitiveArgument extends Argument {
	private Object value;
	private Class<?> represantClass;

	public PrimitiveArgument(String name, EType type, String interfaceName,
			String strVal, ClassLoader classLoader) throws Throwable {
		super(name);
		this.represantClass = this.loadClass(interfaceName, classLoader);
		this.value = type.getValue(strVal);
	}

	@Override
	public Class<?> getRepresantClass() {
		return this.represantClass;
	}

	@Override
	public Object getValue() {
		return value;
	}
}
