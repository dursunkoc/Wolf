/**
 * 
 */
package com.aric.esb.environment;

import java.util.Map;

/**
 * @author TCDUKOC
 * 
 */
public class MapVariable implements Variable {
	private Map<String, Object> value;
	private String mapName;

	/**
	 * @param mapName
	 * @param variableName
	 */
	public MapVariable(String mapName, Map<String, Object> value) {
		this.mapName = mapName;
		this.value = value;
	}

	/**
	 * @param mapName
	 * @param value
	 * @return
	 */
	public static MapVariable newInstance(String mapName,
			Map<String, Object> value) {
		return new MapVariable(mapName, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.esb.environment.Variable#getValue()
	 */
	@Override
	public Object getValue() {
		return this.value.get(this.mapName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.esb.environment.Variable#isNull()
	 */
	@Override
	public boolean isNull() {
		return false;
	}

}
