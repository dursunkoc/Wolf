package com.aric.esb.resourcefactory;

import com.aric.esb.IWolfService;
import com.aric.esb.exceptions.ResourceFactoryException;

/**
 * @author Dursun KOC
 * 
 */
public interface ResourceFactory extends IWolfService{
	/**
	 * @param resourceName
	 * @return
	 * @throws ResourceFactoryException
	 */
	public Object getResource(String resourceName)
			throws ResourceFactoryException;
}
