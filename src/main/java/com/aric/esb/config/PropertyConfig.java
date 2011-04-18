package com.aric.esb.config;

import java.util.Properties;
import java.util.Set;

import org.apache.commons.digester.Digester;

/**
 * @author Dursun KOC
 * 
 */
public class PropertyConfig extends UnConfigurableConfig {
	private static final String _PROPS_TAG = "properties";
	private static final String _PROP_TAG = "property";
	private static final String _NAME_TAG = "name";
	private static final String _VALUE_TAG = "value";
	private static final String ADD_PROP_CONFIG = "addPropertyConfig";
	private String name;
	private String value;

	public static void registerDigester(String parent, Digester digester) {
		final String myPath = parent + _SEP + _PROPS_TAG + _SEP + _PROP_TAG;
		final String[] properties = new String[] { _NAME_TAG, _VALUE_TAG };
		digester.addObjectCreate(myPath, PropertyConfig.class);
		digester.addSetNext(myPath, ADD_PROP_CONFIG);
		digester.addSetProperties(myPath, properties, properties);
		for (String propertyTag : properties) {
			digester.addBeanPropertySetter(myPath + _SEP + propertyTag,
					propertyTag);
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	/**
	 * @param propertyConfigs
	 * @return
	 */
	public static Properties configure(Set<PropertyConfig> propertyConfigs) {
		Properties properties = new Properties();
		for (PropertyConfig propertyConfig : propertyConfigs) {
			properties.put(propertyConfig.getName(), propertyConfig.getValue());
		}
		return properties;
	}

}
