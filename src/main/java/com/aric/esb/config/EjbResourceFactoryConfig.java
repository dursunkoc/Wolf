package com.aric.esb.config;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.naming.NamingException;

import org.apache.commons.digester.Digester;

import com.aric.esb.exceptions.ConfigurationException;
import com.aric.esb.resourcefactory.EjbResourceFactory;

public class EjbResourceFactoryConfig implements Config {
	private static final String _EJBRESOURCEFACTORY_TAG = "EjbResourceFactory";
	private static final String ADD_EJB_RESOURCE_FACTORY_CONFIG = "addEjbResourceFactoryConfig";

	private String id;
	private Set<PropertyConfig> propertyConfigs;

	public static void registerDigester(String parent, Digester digester) {
		final String myPath = parent + _SEP + _EJBRESOURCEFACTORY_TAG;
		final String[] properties = new String[] { "id" };
		digester.addObjectCreate(myPath, EjbResourceFactoryConfig.class);
		digester.addSetNext(myPath, ADD_EJB_RESOURCE_FACTORY_CONFIG);
		digester.addSetProperties(myPath, properties, properties);
		PropertyConfig.registerDigester(myPath, digester);
	}

	public EjbResourceFactoryConfig() {
		this.propertyConfigs = new HashSet<PropertyConfig>();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.esb.config.Config#configureService()
	 */
	@Override
	public EjbResourceFactory configureService(ConfigurationProcessTrace trace) {
		Properties properties = PropertyConfig.configure(propertyConfigs);
		EjbResourceFactory factory;
		try {
			factory = new EjbResourceFactory(properties);
			factory.setId(this.id);
			return factory;
		} catch (NamingException e) {
			throw new ConfigurationException(e);
		}
	}

}
