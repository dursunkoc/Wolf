/**
 * 
 */
package com.aric.esb.config;

import com.aric.esb.IWolfService;

/**
 * @author Dursun KOC
 * 
 */
public abstract class UnConfigurableConfig implements Config {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.esb.config.Config#configureService()
	 */
	@Override
	public IWolfService configureService(ConfigurationProcessTrace trace) {
		throw new UnsupportedOperationException(
				"This class does not support such operation.");
	}

}
