/**
 * 
 */
package com.aric.esb.config;

import java.util.LinkedList;
import java.util.List;

import com.aric.esb.IWolfService;
import com.aric.esb.WolfService;

/**
 * @author Dursun KOC
 * 
 */
public class ConfigurationProcessTrace {
	public class ProxyRefence {
		private String parentObjectId;
		private String proxyObjectId;

		public String getParentObjectId() {
			return parentObjectId;
		}

		public String getProxyObjectId() {
			return proxyObjectId;
		}

		public void setParentObjectId(String parentObjectId) {
			this.parentObjectId = parentObjectId;
		}

		public void setProxyObjectId(String proxyObjectId) {
			this.proxyObjectId = proxyObjectId;
		}

		public ProxyRefence(String parentObjectId, String proxyObjectId) {
			this.parentObjectId = parentObjectId;
			this.proxyObjectId = proxyObjectId;
		}
	}

	private List<ProxyRefence> proxyReferences;

	/**
	 * 
	 */
	public ConfigurationProcessTrace() {
		this.proxyReferences = new LinkedList<ConfigurationProcessTrace.ProxyRefence>();
	}

	/**
	 * @param parentObjectId
	 * @param proxyObjectId
	 */
	public void addProxyReference(String parentObjectId, String proxyObjectId) {
		this.proxyReferences
				.add(new ProxyRefence(parentObjectId, proxyObjectId));
	}

	public void completeReferenceDefinitions(WolfService wolfService) {
		for (ProxyRefence proxyRef : proxyReferences) {
			final String parentObjectId = proxyRef.getParentObjectId();
			final IWolfService service = wolfService.getService(parentObjectId);
			final String proxyObjectId = proxyRef.getProxyObjectId();
			final IWolfService realService = wolfService
					.getService(proxyObjectId);
			service.completeInternalProxy(proxyObjectId, realService);
		}
	}

}
