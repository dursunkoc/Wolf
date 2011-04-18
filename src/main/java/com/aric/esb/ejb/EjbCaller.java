package com.aric.esb.ejb;

import org.apache.commons.beanutils.MethodUtils;

import com.aric.esb.ArgumentBundle;
import com.aric.esb.Caller;
import com.aric.esb.IWolfService;
import com.aric.esb.config.ProxyResourceFactory;
import com.aric.esb.exceptions.CallerException;
import com.aric.esb.resourcefactory.ResourceFactory;

/**
 * @author Dursun KOC
 * 
 */
public class EjbCaller implements Caller {
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
	private String jndiName;
	private String methodName;
	private ResourceFactory resourceFactory;

	@SuppressWarnings("rawtypes")
	@Override
	public Object[] call(ArgumentBundle argumentBundle) throws CallerException {
		try {
			Object resource = resourceFactory.getResource(jndiName);

			Object[] marshalledArgs = argumentBundle.marshallArguments();
			Class[] marshalledTypes = argumentBundle.marshallArgumentClasses();
			Object result = MethodUtils.invokeMethod(resource, methodName,
					marshalledArgs, marshalledTypes);
			return new Object[] { result };
		} catch (Exception e) {
			throw new CallerException(e);
		}
	}

	public EjbCaller(String jndiName, String methodName,
			ResourceFactory resourceFactory) {
		super();
		this.jndiName = jndiName;
		this.methodName = methodName;
		this.resourceFactory = resourceFactory;
	}

	@Override
	public void completeInternalProxy(String id, IWolfService realService) {
		if (this.resourceFactory.isMe(id)
				&& this.resourceFactory instanceof ProxyResourceFactory) {
			((ProxyResourceFactory) this.resourceFactory)
					.setRealFactory((ResourceFactory) realService);
		}	
	}

}
