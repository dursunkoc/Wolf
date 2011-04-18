package com.aric.esb;

import java.lang.reflect.Constructor;
/**
 * @author Dursun KOC
 *
 */
public enum EType {
	BOOLEAN(java.lang.Boolean.class), INTEGER(java.lang.Integer.class), LONG(
			java.lang.Long.class), STRING(java.lang.String.class), DOUBLE(
			java.lang.Double.class), FLOAT(java.lang.Float.class);
	private Class<?> typeClass;

	EType(Class<?> typeClass) {
		this.typeClass = typeClass;
	}

	public Class<?> getTypeClass() {
		return this.typeClass;
	}

	public Object getValue(String s) throws Throwable {
		// TODO :performance top point!
		Constructor<?> constructor = typeClass.getConstructor(String.class);
		return constructor.newInstance(s);
	}

	public static EType findType(Class<?> clazz) {
		for (EType type : EType.values()) {
			if (type.typeClass.equals(clazz)) {
				return type;
			}
		}
		throw new RuntimeException("Unknown Type Exception");
	}

}
