/**
 * 
 */
package com.aric.esb.config;

import org.apache.commons.digester.Digester;

/**
 * @author Dursun KOC
 * 
 */
public class DBReturnMapEntryConfig extends UnConfigurableConfig {
	private static final String _DBRETURNMAPENTRY_TAG = "DBReturnMapEntry";
	private static final String _INDEX_TAG = "index";
	private static final String _TYPE_TAG = "type";
	private static final String ADD_DBRETURNMAPENTRY_CONFIG = "addDbReturnMapEntryConfig";
	private int index;
	private String type;

	public static void registerDigester(String parent, Digester digester) {
		final String myPath = parent + _SEP + _DBRETURNMAPENTRY_TAG;
		final String[] properties = new String[] { _INDEX_TAG, _TYPE_TAG };
		digester.addObjectCreate(myPath, DBReturnMapEntryConfig.class);
		digester.addSetNext(myPath, ADD_DBRETURNMAPENTRY_CONFIG);
		digester.addSetProperties(myPath, properties, properties);
		for (String propertyTag : properties) {
			digester.addBeanPropertySetter(myPath + _SEP + propertyTag,
					propertyTag);
		}
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
