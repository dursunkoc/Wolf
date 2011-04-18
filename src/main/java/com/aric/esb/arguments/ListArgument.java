package com.aric.esb.arguments;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;
/**
 * @author Dursun KOC
 *
 */
public class ListArgument extends Argument {
	private static final Class<?>[] EMPTY_PARAMETERS = new Class[] {};
	private Object value;

	@SuppressWarnings("unchecked")
	public ListArgument(String name, String baseClassName,
			List<Argument> arguments, ClassLoader classLoader) throws Throwable {
		super(name);
		Class<?> clazz = loadClass(baseClassName, classLoader);
		value = clazz.newInstance();
		Method accessibleMethod = MethodUtils.getAccessibleMethod(clazz, this
				.getGetter(), EMPTY_PARAMETERS);
		List<Object> valueArgs = (List<Object>) accessibleMethod.invoke(value);
		for (Argument argument : arguments) {
			valueArgs.add(argument.getValue());
		}
	}

	@Override
	public Class<?> getRepresantClass() {
		return value.getClass();
	}

	@Override
	public Object getValue() {
		return this.value;
	}

}
