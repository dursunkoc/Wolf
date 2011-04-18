/**
 * 
 */
package com.aric.esb;

import java.util.HashSet;
import java.util.Set;

import com.aric.esb.exceptions.CallerException;
import com.aric.esb.exceptions.NoServiceFoundException;

/**
 * @author Dursun KOC
 * 
 */
public class WolfService implements IWolfService {
	private String id;

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.esb.IWolfService#isMe(java.lang.String)
	 */
	@Override
	public boolean isMe(String id) {
		return this.id.equals(id);
	}

	private Set<IWolfService> services;

	public WolfService() {
		services = new HashSet<IWolfService>();
	}

	public void addService(IWolfService service) {
		services.add(service);
	}
	
	/**
	 * @return
	 */
	public Set<IWolfService> getServices() {
		return services;
	}

	/**
	 * @param id
	 * @return
	 */
	public IWolfService getService(String id) {
		for (IWolfService service : services) {
			if (service.isMe(id)) {
				return service;
			}
		}
		throw new NoServiceFoundException("No such service exists serviceId:'"
				+ id + "'");
	}

	/**
	 * @param id
	 * @return
	 */
	public Caller getCallerService(String id) {
		for (IWolfService service : services) {
			if (service.isMe(id) && service instanceof Caller) {
				return (Caller) service;
			}
		}
		throw new NoServiceFoundException(
				"No such caller service exists serviceId:'" + id + "'");
	}

	/**
	 * @param id
	 * @param argumentBundle
	 * @return
	 * @throws CallerException
	 */
	public Object callService(String id, ArgumentBundle argumentBundle)
			throws CallerException {
		Caller caller = getCallerService(id);
		final Object result = caller.call(argumentBundle);
		return result;
	}

	@Override
	public void completeInternalProxy(String id, IWolfService realService) {
		//Nothing to do
	}
}
