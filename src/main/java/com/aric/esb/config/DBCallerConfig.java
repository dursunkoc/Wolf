/**
 * 
 */
package com.aric.esb.config;

import com.aric.esb.db.DBCaller;
import com.aric.esb.exceptions.CallerException;
import com.aric.esb.exceptions.ConfigurationException;
import com.aric.esb.resourcefactory.ResourceFactory;

/**
 * @author Dursun KOC
 * 
 */
public abstract class DBCallerConfig implements Config {
	protected String id;
	protected String dbMethodName;
	protected String connectionJndiName;
	protected String resourceFactoryId;
	protected String returnMapId;
	protected DBResourceFactoryConfig dbResourceFactoryConfig;
	protected DBReturnMapConfig dbReturnMapConfig;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the dbMethodName
	 */
	public String getDbMethodName() {
		return dbMethodName;
	}

	/**
	 * @param dbMethodName
	 *            the dbMethodName to set
	 */
	public void setDbMethodName(String dbMethodName) {
		this.dbMethodName = dbMethodName;
	}

	/**
	 * @return the connectionJndiName
	 */
	public String getConnectionJndiName() {
		return connectionJndiName;
	}

	/**
	 * @param connectionJndiName
	 *            the connectionJndiName to set
	 */
	public void setConnectionJndiName(String connectionJndiName) {
		this.connectionJndiName = connectionJndiName;
	}

	/**
	 * @return the resourceFactoryId
	 */
	public String getResourceFactoryId() {
		return resourceFactoryId;
	}

	/**
	 * @param resourceFactoryId
	 *            the resourceFactoryId to set
	 */
	public void setResourceFactoryId(String resourceFactoryId) {
		this.resourceFactoryId = resourceFactoryId;
	}

	/**
	 * @return the returnMapId
	 */
	public String getReturnMapId() {
		return returnMapId;
	}

	/**
	 * @param returnMapId
	 *            the returnMapId to set
	 */
	public void setReturnMapId(String returnMapId) {
		this.returnMapId = returnMapId;
	}

	/**
	 * @return the dbResourceFactoryConfig
	 */
	public DBResourceFactoryConfig getDbResourceFactoryConfig() {
		return dbResourceFactoryConfig;
	}

	/**
	 * @param dbResourceFactoryConfig
	 *            the dbResourceFactoryConfig to set
	 */
	public void setDbResourceFactoryConfig(
			DBResourceFactoryConfig dbResourceFactoryConfig) {
		this.dbResourceFactoryConfig = dbResourceFactoryConfig;
	}

	/**
	 * @param dbResourceFactoryConfig
	 *            the dbResourceFactoryConfig to set
	 */
	public void addDBResourceFactoryConfig(
			DBResourceFactoryConfig dbResourceFactoryConfig) {
		this.dbResourceFactoryConfig = dbResourceFactoryConfig;
	}

	/**
	 * @return the dbReturnMapConfig
	 */
	public DBReturnMapConfig getDbReturnMapConfig() {
		return dbReturnMapConfig;
	}

	/**
	 * @param dbReturnMapConfig
	 *            the dbReturnMapConfig to set
	 */
	public void setDbReturnMapConfig(DBReturnMapConfig dbReturnMapConfig) {
		this.dbReturnMapConfig = dbReturnMapConfig;
	}

	/**
	 * @param dbReturnMapConfig
	 *            the dbReturnMapConfig to set
	 */
	public void addDBReturnMapConfig(DBReturnMapConfig dbReturnMapConfig) {
		this.dbReturnMapConfig = dbReturnMapConfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.esb.config.Config#configureService(com.aric.esb.config.
	 * ConfigurationProcessTrace)
	 */
	@Override
	public DBCaller configureService(ConfigurationProcessTrace trace) {
		try {
			String methodName = this.dbMethodName;
			ReturnMap returnMap = this.getReturnMap(trace);
			ResourceFactory resourceFactory = this.getResourceFactory(trace);
			String connectionName = this.connectionJndiName;
			DBCaller caller = prepareCaller(methodName, returnMap,
					resourceFactory, connectionName);
			return caller;
		} catch (CallerException e) {
			throw new ConfigurationException(e);
		}
	}

	protected abstract DBCaller prepareCaller(String methodName,
			ReturnMap returnMap, ResourceFactory resourceFactory,
			String connectionName) throws CallerException;

	/**
	 * @param trace
	 * @param returnMap
	 * @return
	 */
	protected ReturnMap getReturnMap(ConfigurationProcessTrace trace) {
		if (dbReturnMapConfig != null) {
			return (ReturnMap) this.dbReturnMapConfig.configureService(trace);
		} else if (returnMapId != null) {
			trace.addProxyReference(this.id, returnMapId);
			return new ProxyReturnMap(returnMapId);
		} else {
			throw new ConfigurationException(
					"Either returnMap or returnMapId should be provided. (DBFunctionCaller.id='"
							+ this.id + "')");
		}
	}

	/**
	 * @param trace
	 * @return
	 */
	protected ResourceFactory getResourceFactory(ConfigurationProcessTrace trace) {
		if (dbResourceFactoryConfig != null) {
			return this.dbResourceFactoryConfig.configureService(trace);
		} else if (this.resourceFactoryId != null) {
			trace.addProxyReference(this.id, this.resourceFactoryId);
			return new ProxyResourceFactory(this.resourceFactoryId);
		} else {
			throw new ConfigurationException(
					"Either resourceFactory or resourceFactoryId should be provided. (DBFunctionCaller.id='"
							+ this.id + "')");
		}
	}

}
