/**
 * 
 */
package com.aric.esb.config;

import javax.xml.namespace.QName;

import org.apache.commons.digester.Digester;

import com.aric.esb.exceptions.ConfigurationException;
import com.aric.esb.resourcefactory.ResourceFactory;
import com.aric.esb.resourcefactory.WsResourceFactory;
import com.aric.esb.ws.WsCaller;

/**
 * @author Dursun KOC
 * 
 */
public class WSCallerConfig implements Config {

	private static final String _WSCALLER_TAG = "WSCaller";
	private static final String ADD_DB_WS_CALLER_CONFIG = "addWSCallerConfig";
	private String id;
	private String wsdlUrl;
	private String operationName;

	public static void registerDigester(String parent, Digester digester) {
		final String myPath = parent + _SEP + _WSCALLER_TAG;
		final String[] properties = new String[] { "id", "wsdlUrl",
				"operationName" };

		digester.addObjectCreate(myPath, WSCallerConfig.class);
		digester.addSetNext(myPath, ADD_DB_WS_CALLER_CONFIG);
		digester.addSetProperties(myPath, properties, properties);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}

	public String getId() {
		return id;
	}

	public String getOperationName() {
		return operationName;
	}

	public String getWsdlUrl() {
		return wsdlUrl;
	}

	@Override
	public WsCaller configureService(ConfigurationProcessTrace trace) {
		try {
			WsCaller caller;
			ResourceFactory resourceFactory = new WsResourceFactory();
			caller = new WsCaller(new QName(operationName), wsdlUrl,
					resourceFactory);
			return caller;
		} catch (Exception e) {
			throw new ConfigurationException(e);
		}
	}

}
