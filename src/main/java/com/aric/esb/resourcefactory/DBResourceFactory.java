/**
 * 
 */
package com.aric.esb.resourcefactory;

import java.sql.Connection;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.aric.esb.IWolfService;
import com.aric.esb.exceptions.ResourceFactoryException;

/**
 * @author Dursun KOC
 * 
 */
public class DBResourceFactory implements ResourceFactory {
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

	public DBResourceFactory(Properties properties) throws NamingException {
		this.initialContext = new InitialContext(properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.esb.resourcefactory.ResourceFactory#getResource(java.lang.String
	 * )
	 */
	@Override
	public Connection getResource(String resourceName)
			throws ResourceFactoryException {
		try {
			DataSource ds = (DataSource) initialContext.lookup(resourceName);
			return ds.getConnection();
		} catch (Exception e) {
			throw new ResourceFactoryException(e);
		}
	}

	@Override
	public void completeInternalProxy(String id, IWolfService realService) {
		// Nothing to do
	}

}
