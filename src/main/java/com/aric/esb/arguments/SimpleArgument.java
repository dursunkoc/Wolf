package com.aric.esb.arguments;

/**
 * @author Dursun KOC
 *
 */
public class SimpleArgument extends Argument {
	private Object value;
	private Class<?> represantClass;

	public SimpleArgument(String name, Object value) {
		super(name);
		if (value == null) {
			throw new RuntimeException("value cannot be null!");
		}
		this.value = value;
	}

	public SimpleArgument(String name, Object value, Class<?> represantClass) {
		super(name);
		if (value == null && represantClass == null) {
			throw new RuntimeException(
					"Both value and represantClass cannot be null at the same time.");
		}
		if (value != null && represantClass != null
				&& !represantClass.isAssignableFrom(value.getClass())) {
			throw new RuntimeException(
					"represantClass is not assignable from value");
		}
		this.value = value;
		this.represantClass = represantClass;
	}

	@Override
	public Class<?> getRepresantClass() {
		if (this.represantClass != null) {
			return this.represantClass;
		} else if (value != null) {
			return value.getClass();
		} else {
			return null;
		}
	}

	@Override
	public Object getValue() {
		return value;
	}

}
