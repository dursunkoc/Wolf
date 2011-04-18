/**
 * 
 */
package com.aric.esb.util;

import java.util.Map;

import com.aric.esb.environment.Variable;
import com.aric.esb.exceptions.BindVariableDoesNotExists;

/**
 * @author Dursun KOC
 * 
 */
public abstract class StringUtils {
	private static final String _DEFAULT_VARIABLE_PREFIX = "${";
	private static final String _DEFAULT_VARIABLE_SUFFIX = "}";
	private static final String _VARIABLE_ESCAPE_CHAR = "#";

	public static Object ifNull(Object obj, Object nullValue) {
		if (obj == null) {
			return nullValue;
		} else if (obj instanceof Variable) {
			if (((Variable) obj).isNull()) {
				return nullValue;
			}
		}
		return obj;
	}

	public static Object ifNull(Variable var, Object nullValue) {
		if (var.isNull()) {
			return nullValue;
		}
		return var;
	}

	public static String ifNullThenEmtpyString(Object obj) {
		return ifNull(obj, "").toString();
	}

	/**
	 * replace ${aaa} variables within the strVal.
	 * 
	 * @param strVal
	 * @param maps
	 * @return
	 * @throws BindVariableDoesNotExists
	 */
	@SuppressWarnings("rawtypes")
	public static String replaceMappedVariables(String strVal, Map maps)
			throws BindVariableDoesNotExists {
		StringBuffer buf = new StringBuffer(ifNullThenEmtpyString(strVal));

		int startIndex = buf.indexOf(_DEFAULT_VARIABLE_PREFIX);
		while (startIndex != -1) {
			int endIndex = findVariableEndIndex(buf, startIndex);
			if (isEscaped(startIndex, endIndex, buf)) {
				startIndex = buf.indexOf(_DEFAULT_VARIABLE_PREFIX, endIndex);
				endIndex = findVariableEndIndex(buf, startIndex);
			}
			if (endIndex != -1) {
				String varName = buf.substring(startIndex
						+ _DEFAULT_VARIABLE_PREFIX.length(), endIndex);
				if (!maps.containsKey(varName)) {
					throw new BindVariableDoesNotExists("Variable('" + varName
							+ "') does not exist in the map!", varName);
				}
				String varValue = ifNullThenEmtpyString(maps.get(varName));
				buf.replace(startIndex, endIndex
						+ _DEFAULT_VARIABLE_SUFFIX.length(), varValue);
				buf = new StringBuffer(replaceMappedVariables(buf.toString(),
						maps));
				strVal = buf.toString();
				startIndex = strVal.indexOf(_DEFAULT_VARIABLE_PREFIX);
			} else {
				startIndex = -1;
			}
		}

		return buf.toString();
	}

	public static String removeMappedVariableEscape(String strVal) {
		StringBuffer buf = new StringBuffer(ifNullThenEmtpyString(strVal));
		int startIndex = strVal.indexOf(_DEFAULT_VARIABLE_PREFIX);
		while (startIndex != -1) {
			int endIndex = findVariableEndIndex(buf, startIndex);
			if (endIndex != -1) {
				if (isEscaped(startIndex, endIndex, buf)) {
					int start_minusTwo = startIndex - 1;
					int start_minusOne = startIndex;
					buf.delete(start_minusTwo, start_minusOne);
				}
				startIndex = buf.indexOf(_DEFAULT_VARIABLE_PREFIX, endIndex);
			} else {
				startIndex = -1;
			}
		}
		return buf.toString();
	}

	/**
	 * @param startIndex
	 * @param endIndex
	 * @param buf
	 * @return
	 */
	private static boolean isEscaped(int startIndex, int endIndex,
			StringBuffer buf) {
		int start_minusTwo = startIndex - 1;
		int start_minusOne = startIndex;
		if (start_minusTwo < 0) {
			return false;
		}

		if (buf.substring(start_minusTwo, start_minusOne).equals(
				_VARIABLE_ESCAPE_CHAR)) {
			return true;
		}
		return false;
	}

	/**
	 * @param buf
	 * @param startIndex
	 * @return
	 */
	private static int findVariableEndIndex(CharSequence buf, int startIndex) {
		int index = startIndex + _DEFAULT_VARIABLE_PREFIX.length();
		int withinNestedVariable = 0;
		while (index < buf.length()) {
			if (substringMatch(buf, index, _DEFAULT_VARIABLE_SUFFIX)) {
				if (withinNestedVariable > 0) {
					withinNestedVariable--;
					index = index + _DEFAULT_VARIABLE_SUFFIX.length();
				} else {
					return index;
				}
			} else if (substringMatch(buf, index, _DEFAULT_VARIABLE_PREFIX)) {
				withinNestedVariable++;
				index = index + _DEFAULT_VARIABLE_PREFIX.length();
			} else {
				index++;
			}
		}
		return -1;
	}

	private static boolean substringMatch(CharSequence str, int index,
			CharSequence substring) {
		for (int j = 0; j < substring.length(); j++) {
			int i = index + j;
			if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
				return false;
			}
		}
		return true;
	}
}
