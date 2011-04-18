/**
 * 
 */
package com.aric.esb.config;

import java.util.Map;

import com.aric.esb.IWolfService;

/**
 * @author Dursun KOC
 * 
 */
public abstract class ReturnMap implements Map<Integer, Integer>, IWolfService {
	private String id;

	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 * @return
	 */
	public boolean isMe(String id) {
		return this.id.equals(id);
	}

	/**
	 * @param id
	 */
	public ReturnMap(String id) {
		this.id = id;
	}

}
