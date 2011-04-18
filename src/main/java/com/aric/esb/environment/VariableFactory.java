package com.aric.esb.environment;

import java.util.Map;

import com.aric.esb.util.StringUtils;

/**
 * @author TCDUKOC
 * 
 */
public final class VariableFactory {

	/**
	 * @param value
	 * @return
	 */
	public static Variable newInstance(Object value) {
		if (value == null) {
			return NullVariable.getInstance();
		} else {
			return FullVariable.getNewInstance(value);
		}
	}

	/**
	 * @param value
	 * @return
	 */
	public static Variable newInstance(Map<String, Object> value, String mapName) {
		if (value == null) {
			return NullVariable.getInstance();
		} else if (StringUtils.ifNull(mapName, "").equals("")) {
			return FullVariable.getNewInstance(value);
		} else {
			return MapVariable.newInstance(mapName, value);
		}
	}
}
