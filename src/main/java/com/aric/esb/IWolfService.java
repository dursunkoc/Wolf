/**
 * 
 */
package com.aric.esb;

/**
 * @author Dursun KOC
 *
 */
public interface IWolfService {
	public static final String UNKNOWN_ID = "unknown"; 
	/**
	 * @return
	 */
	public String getId();
	
	/**
	 * @param id
	 * @return
	 */
	public boolean isMe(String id);
	
	/**
	 * @param id
	 * @param realService
	 */
	public void completeInternalProxy(String id, IWolfService realService);

}
