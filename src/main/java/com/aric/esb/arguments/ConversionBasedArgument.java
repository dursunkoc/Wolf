package com.aric.esb.arguments;

import org.apache.commons.beanutils.MethodUtils;
/**
 * @author Dursun KOC
 *
 */
public class ConversionBasedArgument extends Argument {
	private Object value;
	private Class<?> represantClass;

	public ConversionBasedArgument(String name, String converterClassName,
			String converterMethodName, String interfaceName,
			Argument baseArgument, ClassLoader classLoader) throws Throwable {
		super(name);
		this.represantClass = classLoader.loadClass(interfaceName);
		Class<?> converterClass = classLoader.loadClass(converterClassName);
		this.value = MethodUtils.invokeStaticMethod(converterClass,
				converterMethodName, baseArgument.getValue());
	}

	@Override
	public Class<?> getRepresantClass() {
		return this.represantClass;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

}
