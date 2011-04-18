package com.aric.esb.resourcefactory;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;

import com.aric.esb.IWolfService;
import com.aric.esb.exceptions.ResourceFactoryException;

/**
 * @author Dursun KOC
 * 
 */
public class WsResourceFactory extends CacheableResourceFactory {
	private String id;
	
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
	private static final DynamicClientFactory clientFactory = DynamicClientFactory
			.newInstance();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.esb.resourcefactory.AbstractSimpleResourceFactory#createResource
	 * (java.lang.String)
	 */
	@Override
	protected Object createResource(String wsdlUrl)
			throws ResourceFactoryException {
		Client client = clientFactory.createClient(wsdlUrl);
		return client;
	}

	@Override
	public void completeInternalProxy(String id, IWolfService realService) {
		// Nothing to do
		
	}

}
