package com.aric.esb.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.MethodUtils;

import com.aric.esb.exceptions.DynamicCallerException;

/**
 * @author Dursun KOC
 * 
 */
public class DynamicCaller {
	private static final String _LIST_POSTFIX = "}";
	public final static String _LIST_PREFIX = "{list";
	/**
	 * @param callPattern
	 * @param result
	 * @return
	 * @throws Throwable
	 */
	public static Object call(StringTokenizer callPattern, Class<?> result)
			throws Throwable {
		return callRecursively(callPattern, result);
	}

	/**
	 * @param callPattern
	 * @param result
	 * @return
	 * @throws Throwable
	 */
	public static Object call(String callPattern, Class<?> result)
			throws Throwable {
		if (StringUtils.ifNullThenEmtpyString(callPattern).trim().isEmpty()) {
			return result;
		}
		StringTokenizer stringTokenizer = new StringTokenizer(callPattern, ".");
		return callRecursively(stringTokenizer, result);
	}

	/**
	 * 
	 * @param callPattern
	 * @param result
	 * @return
	 * @throws FunctionExecutionException
	 */
	public static Object call(String callPattern, Object result) {
		if (StringUtils.ifNullThenEmtpyString(callPattern).trim().isEmpty()) {
			return result;
		}
		StringTokenizer stringTokenizer = new StringTokenizer(callPattern, ".");
		return callRecursively(stringTokenizer, result);
	}

	/**
	 * 
	 * @param callPattern
	 * @param result
	 * @return
	 * @throws FunctionExecutionException
	 */
	public static Object call(StringTokenizer callPattern, Object result) {
		return callRecursively(callPattern, result);
	}

	/**
	 * 
	 * @param callPattern
	 * @param result
	 * @return
	 * @throws FunctionExecutionException
	 */
	private static Object callRecursively(StringTokenizer callPattern,
			Object result) {
		if (callPattern.hasMoreTokens()) {
			String methodName = callPattern.nextToken();
			Object myResult = callFirstLevel(methodName, result);
			return callRecursively(callPattern, myResult);
		} else {
			return result;
		}
	}

	/**
	 * 
	 * @param callPattern
	 * @param result
	 * @return
	 * @throws Throwable
	 * @throws FunctionExecutionException
	 */
	private static Object callRecursively(StringTokenizer callPattern,
			Class<?> result) throws Throwable {
		if (callPattern.hasMoreTokens()) {
			String methodName = callPattern.nextToken();
			Object myResult = callFirstLevel(methodName, result);
			return callRecursively(callPattern, myResult);
		} else {
			return result;
		}
	}

	private static Object callFirstLevel(String methodName, Class<?> result)
			throws Throwable {
		Method eligibleMethod = MethodUtils.getAccessibleMethod(result,
				methodName, new Class[] {});
		Object value = eligibleMethod.invoke(result);
		return value;
	}

	/**
	 * 
	 * @param methodName
	 * @param result
	 * @return
	 * @throws FunctionExecutionException
	 */
	private static Object callFirstLevel(String methodName, Object result) {
		try {
			if (StringUtils.ifNullThenEmtpyString(methodName).trim().isEmpty()) {
				return result;
			}
			Object retVal = null;
			if (isList(methodName, result)) {
				retVal = callListMethod(methodName, result, retVal);
			} else {
				retVal = callObjectMethod(methodName, result);
			}
			return retVal;
		} catch (Exception e) {
			throw new DynamicCallerException(e);
		}
	}

	/**
	 * @param methodName
	 * @param result
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static Object callObjectMethod(String methodName, Object result)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Method eligibleGetter = MethodUtils.getAccessibleMethod(result
				.getClass(), methodName, new Class[] {});
		Object value = eligibleGetter.invoke(result);
		return value;
	}

	/**
	 * @param getterMethodName
	 * @param result
	 * @param retVal
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object callListMethod(String getterMethodName,
			Object result, Object retVal) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		List listOf_result = (List) result;
		if (getterMethodName.matches("\\"+_LIST_PREFIX+"\\([0-9]+\\)\\"+_LIST_POSTFIX)) {
			Integer index = Integer.parseInt(getterMethodName.substring(6, 7));
			Object elementToGet = listOf_result.get(index);
			return elementToGet;
		} else
		// if (getterMethodName.matches("\\"+_LIST_PREFIX+"\\(\\w+\\)\\"+_LIST_POSTFIX))
		{
			String listMethod = getterMethodName.substring(6, getterMethodName
					.length() - 2);
			List listOfReturn = new ArrayList();
			Method eligibleListGetter = listOf_result.get(0).getClass()
					.getMethod(listMethod);
			for (Object object : listOf_result) {
				listOfReturn.add(eligibleListGetter.invoke(object));
			}
			return listOfReturn;
		}
	}

	/**
	 * @param getterMethodName
	 * @return
	 * @throws FunctionExecutionException
	 */
	private static boolean isList(String getterMethodName, Object object) {
		boolean isStringList = getterMethodName.startsWith(_LIST_PREFIX)
				&& getterMethodName.endsWith(_LIST_POSTFIX);
		if (isStringList) {
			if (!(object instanceof List)) {
				throw new DynamicCallerException("Object(" + object
						+ ") is not a List! Cannot use "+_LIST_PREFIX+_LIST_POSTFIX);
			}
		}
		return isStringList;
	}
}
