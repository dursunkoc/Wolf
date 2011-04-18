package com.aric.esb.arguments;

import java.util.List;

import com.aric.esb.util.DynamicCaller;
/**
 * @author Dursun KOC
 *
 */
public class FactoryBasedArgument extends Argument {
	private Object value;
	private Class<?> represantClass;

	public FactoryBasedArgument(String name, String factoryClassName,
			String factoryMethodChain, String interfaceName,
			List<Argument> arguments, ClassLoader classLoader) throws Throwable {
		super(name);
		this.represantClass = classLoader.loadClass(interfaceName);
		Class<?> factoryClass = classLoader.loadClass(factoryClassName);
		this.value = DynamicCaller.call(factoryMethodChain, factoryClass);
		this.injectArguments(arguments, value);
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
