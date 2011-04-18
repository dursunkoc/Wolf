package com.aric.esb.arguments;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.WordUtils;

/**
 * @author Dursun KOC
 *
 */
public abstract class Argument {
	private String name;

	/**
	 * @param name
	 */
	public Argument(String name) {
		if (name == null) {
			throw new RuntimeException("name cannot be null!!!");
		}
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getSetter() {
		String setterName = "set" + WordUtils.capitalize(this.name);
		return setterName;
	}

	/**
	 * @return
	 */
	public String getGetter() {
		String getterName = "get" + WordUtils.capitalize(this.name);
		return getterName;
	}

	/**
	 * @param arguments
	 * @param instance
	 * @throws Throwable
	 */
	protected void injectArguments(List<Argument> arguments, Object instance)
			throws Throwable {
		for (Argument argument : arguments) {
			Method accessibleMethod = MethodUtils.getAccessibleMethod(instance
					.getClass(), argument.getSetter(), argument
					.getRepresantClass());
			accessibleMethod.invoke(instance, argument.getValue());
		}
	}

	/**
	 * @param className
	 * @param classLoader
	 * @return
	 * @throws ClassNotFoundException
	 */
	protected Class<?> loadClass(String className, ClassLoader classLoader)
			throws ClassNotFoundException {
		Class<?> clazz = primitiveClass(className);
		if (clazz != null) {
			return clazz;
		}
		clazz = classLoader.loadClass(className);
		return clazz;
	}

	/**
	 * @param className
	 * @return
	 */
	private Class<?> primitiveClass(String className) {
		if (className.equals("boolean")) {
			return boolean.class;
		} else if (className.equals("char")) {
			return char.class;
		} else if (className.equals("byte")) {
			return byte.class;
		} else if (className.equals("short")) {
			return short.class;
		} else if (className.equals("int")) {
			return int.class;
		} else if (className.equals("long")) {
			return long.class;
		} else if (className.equals("float")) {
			return float.class;
		} else if (className.equals("double")) {
			return double.class;
		} else {
			return null;
		}
	}

	/**
	 * @return
	 */
	public abstract Class<?> getRepresantClass();

	/**
	 * @return
	 */
	public abstract Object getValue();
}
