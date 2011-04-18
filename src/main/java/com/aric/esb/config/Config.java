/**
 * 
 */
package com.aric.esb.config;

import com.aric.esb.IWolfService;

/**
 * @author Dursun KOC
 * 
 */
public interface Config {
	public static final String _SEP = "/";

	/**
	 * @return
	 */
	public IWolfService configureService(ConfigurationProcessTrace trace);
}
