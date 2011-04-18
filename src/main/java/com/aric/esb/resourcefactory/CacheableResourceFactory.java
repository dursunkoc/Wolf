package com.aric.esb.resourcefactory;

import java.util.HashMap;
import java.util.Map;

import com.aric.esb.exceptions.ResourceFactoryException;

public abstract class CacheableResourceFactory implements ResourceFactory {
	private Map<Integer, Object> resourceRepository = new HashMap<Integer, Object>();

	@Override
	public Object getResource(String resourceName)
			throws ResourceFactoryException {
		int resourceHashCode = resourceName.hashCode();
		if (!resourceRepository.containsKey(resourceHashCode)) {
			Object resource = createResource(resourceName);
			resourceRepository.put(resourceHashCode, resource);
		}
		return resourceRepository.get(resourceHashCode);
	}

	protected abstract Object createResource(String resourceName)
			throws ResourceFactoryException;

}
