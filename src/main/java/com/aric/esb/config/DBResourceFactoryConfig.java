/**
 * 
 */
package com.aric.esb.config;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.naming.NamingException;

import org.apache.commons.digester.Digester;

import com.aric.esb.exceptions.ConfigurationException;
import com.aric.esb.resourcefactory.DBResourceFactory;

/**
 * @author Dursun KOC
 * 
 */
public class DBResourceFactoryConfig implements Config {
	private static final String _DBRESOURCEFACTORY_TAG = "DBResourceFactory";
	private static final String ADD_DB_RESOURCE_FACTORY_CONFIG = "addDBResourceFactoryConfig";
	private String id;
	private Set<PropertyConfig> propertyConfigs;

	public DBResourceFactoryConfig() {
		this.propertyConfigs = new HashSet<PropertyConfig>();
	}

	public static void registerDigester(String parent, Digester digester) {
		final String myPath = parent + _SEP + _DBRESOURCEFACTORY_TAG;
		final String[] properties = new String[] { "id" };
		digester.addObjectCreate(myPath, DBResourceFactoryConfig.class);
		digester.addSetNext(myPath, ADD_DB_RESOURCE_FACTORY_CONFIG);
		digester.addSetProperties(myPath, properties, properties);
		PropertyConfig.registerDigester(myPath, digester);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Set<PropertyConfig> getPropertyConfigs() {
		return propertyConfigs;
	}

	public void setPropertyConfigs(Set<PropertyConfig> propertyConfigs) {
		this.propertyConfigs = propertyConfigs;
	}

	public void addPropertyConfig(PropertyConfig propertyConfig) {
		this.propertyConfigs.add(propertyConfig);
	}

	@Override
	public DBResourceFactory configureService(ConfigurationProcessTrace trace) {
		try {
			Properties properties = PropertyConfig.configure(propertyConfigs);
			DBResourceFactory factory = new DBResourceFactory(properties);
			factory.setId(this.id);
			return factory;
		} catch (NamingException e) {
			throw new ConfigurationException(e);
		}
	}

}
