/**
 * 
 */
package com.aric.esb.config;

import org.apache.commons.digester.Digester;

import com.aric.esb.ejb.EjbCaller;
import com.aric.esb.exceptions.ConfigurationException;
import com.aric.esb.resourcefactory.ResourceFactory;

/**
 * @author Dursun KOC
 * 
 */
public class EJBCallerConfig implements Config {

	private static final String _EJBCALLER_TAG = "EJBCaller";
	private static final String ADD_EJB_CALLER_CONFIG = "addEjbCallerConfig";

	private String id;
	private String jndiName;
	private String methodName;
	private String resourceFactoryId;

	private EjbResourceFactoryConfig ejbResourceFactoryConfig;

	public static void registerDigester(String parent, Digester digester) {
		final String myPath = parent + "/" + _EJBCALLER_TAG;
		final String[] properties = new String[] { "id", "jndiName",
				"methodName", "resourceFactoryId" };
		digester.addObjectCreate(myPath, EJBCallerConfig.class);
		digester.addSetNext(myPath, ADD_EJB_CALLER_CONFIG);

		digester.addSetProperties(myPath, properties, properties);
		EjbResourceFactoryConfig.registerDigester(myPath, digester);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setResourceFactoryId(String resourceFactoryId) {
		this.resourceFactoryId = resourceFactoryId;
	}

	public void setEjbResourceFactoryConfig(
			EjbResourceFactoryConfig ejbResourceFactoryConfig) {
		this.ejbResourceFactoryConfig = ejbResourceFactoryConfig;
	}

	public void addEjbResourceFactoryConfig(
			EjbResourceFactoryConfig ejbResourceFactoryConfig) {
		this.ejbResourceFactoryConfig = ejbResourceFactoryConfig;
	}

	public String getId() {
		return id;
	}

	public String getJndiName() {
		return jndiName;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getResourceFactoryId() {
		return resourceFactoryId;
	}

	public EjbResourceFactoryConfig getEjbResourceFactoryConfig() {
		return ejbResourceFactoryConfig;
	}

	@Override
	public EjbCaller configureService(ConfigurationProcessTrace trace) {
		ResourceFactory resourceFactory = getResourceFactory(trace);
		EjbCaller caller = new EjbCaller(jndiName, methodName, resourceFactory);
		caller.setId(id);
		return caller;
	}

	/**
	 * @param trace
	 * @return
	 */
	protected ResourceFactory getResourceFactory(ConfigurationProcessTrace trace) {
		if (ejbResourceFactoryConfig != null) {
			return this.ejbResourceFactoryConfig.configureService(trace);
		} else if (this.resourceFactoryId != null) {
			trace.addProxyReference(this.id, this.resourceFactoryId);
			return new ProxyResourceFactory(this.resourceFactoryId);
		} else {
			throw new ConfigurationException(
					"Either resourceFactory or resourceFactoryId should be provided. (EJBCaller.id='"
							+ this.id + "')");
		}
	}

}
