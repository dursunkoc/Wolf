package com.aric.esb.resourcefactory;

import java.util.Properties;

import javax.ejb.EJBHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.beanutils.MethodUtils;

import com.aric.esb.IWolfService;
import com.aric.esb.exceptions.ResourceFactoryException;

/**
 * @author Dursun KOC
 * 
 */
public class EjbResourceFactory extends CacheableResourceFactory {
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
	private Context initialContext;

	public EjbResourceFactory(Properties properties) throws NamingException {
		this.initialContext = new InitialContext(properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.esb.resourcefactory.AbstractSimpleResourceFactory#createResource
	 * (java.lang.String)
	 */
	@Override
	protected Object createResource(String resourceName)
			throws ResourceFactoryException {
		try {
			Object ejbInt = initialContext.lookup(resourceName);
			if (ejbInt instanceof EJBHome) {
				ejbInt = MethodUtils.invokeMethod(ejbInt, "create", null);
			}
			return ejbInt;
		} catch (Exception e) {
			throw new ResourceFactoryException(e);
		}
	}

	@Override
	public void completeInternalProxy(String id, IWolfService realService) {
		// Nothing to do
		
	}

}
