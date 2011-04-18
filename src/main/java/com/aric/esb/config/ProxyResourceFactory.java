/**
 * 
 */
package com.aric.esb.config;

import com.aric.esb.IWolfService;
import com.aric.esb.exceptions.ResourceFactoryException;
import com.aric.esb.resourcefactory.ResourceFactory;

/**
 * @author Dursun KOC
 * 
 */
public class ProxyResourceFactory implements ResourceFactory {
	private ResourceFactory realFactory;
	private String id;

	/**
	 * @param id
	 */
	public ProxyResourceFactory(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.esb.IWolfService#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.esb.IWolfService#isMe(java.lang.String)
	 */
	@Override
	public boolean isMe(String id) {
		return id.equals(this.id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.esb.resourcefactory.ResourceFactory#getResource(java.lang.String
	 * )
	 */
	@Override
	public Object getResource(String resourceName)
			throws ResourceFactoryException {
		return this.realFactory.getResource(resourceName);
	}

	/**
	 * @param factory
	 */
	public void setRealFactory(ResourceFactory factory) {
		this.realFactory = factory;
	}

	@Override
	public void completeInternalProxy(String id, IWolfService realService) {
		//Nothing to do
	}
}
