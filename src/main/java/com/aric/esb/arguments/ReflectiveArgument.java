package com.aric.esb.arguments;

import java.util.Arrays;
import java.util.List;
/**
 * @author Dursun KOC
 *
 */
public class ReflectiveArgument extends Argument {
	private Object value;
	private Class<?> represantClass;

	public ReflectiveArgument(String name, String className,
			String interfaceName, List<Argument> arguments,
			ClassLoader classLoader) throws Throwable {
		super(name);
		Class<?> clazz = this.loadClass(className, classLoader);
		this.represantClass = this.loadClass(interfaceName, classLoader);
		this.value = initializeValue(arguments, clazz);
	}

	public ReflectiveArgument(String name, String className,
			String interfaceName, Argument[] args, ClassLoader classLoader)
			throws Throwable {
		this(name, className, interfaceName, Arrays.asList(args), classLoader);
	}

	private Object initializeValue(List<Argument> arguments, Class<?> clazz)
			throws Throwable {
		Object value = clazz.newInstance();
		injectArguments(arguments, value);
		return value;
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