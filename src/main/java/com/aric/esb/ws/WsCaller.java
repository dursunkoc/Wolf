package com.aric.esb.ws;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;

import com.aric.esb.ArgumentBundle;
import com.aric.esb.Caller;
import com.aric.esb.IWolfService;
import com.aric.esb.exceptions.CallerException;
import com.aric.esb.exceptions.ResourceFactoryException;
import com.aric.esb.resourcefactory.ResourceFactory;

/**
 * @author Dursun KOC
 * 
 */
public class WsCaller implements Caller {
	private String id;
	private QName operationName;
	private Client dynClient;
	
	/* (non-Javadoc)
	 * @see com.aric.esb.IWolfService#getId()
	 */
	@Override
	public String getId() {
		return id;
	}
	
	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see com.aric.esb.IWolfService#isMe(java.lang.String)
	 */
	@Override
	public boolean isMe(String id) {
		if(this.id==null){
			return false;
		}
		return this.id.equals(id);
	}	

	@Override
	public Object[] call(ArgumentBundle argumentBundle) throws CallerException {
		try {
			Object[] marshalledArgs = argumentBundle.marshallArguments();
			Object[] result = dynClient.invoke(operationName, marshalledArgs);
			return result;
		} catch (Exception e) {
			throw new CallerException(e);
		}
	}

	/**
	 * @param operationName
	 * @param wsdlUrl
	 * @throws CallerException
	 * @throws ResourceException
	 */
	public WsCaller(QName operationName, String wsdlUrl,
			ResourceFactory resourceFactory) throws CallerException,
			ResourceFactoryException {
		this.operationName = operationName;
		Object resource = resourceFactory.getResource(wsdlUrl);
		if (!(resource instanceof Client)) {
			throw new CallerException(
					"WsCaller expected a \"org.apache.cxf.endpoint.Client\" instance but got \""
							+ resource.getClass() + "\" instance!");
		}
		dynClient = (Client) resource;
	}

	@Override
	public void completeInternalProxy(String id, IWolfService realService) {
		// Nothing to do
	}

}
