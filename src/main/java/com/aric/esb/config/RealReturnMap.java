/**
 * 
 */
package com.aric.esb.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.aric.esb.IWolfService;

/**
 * @author Dursun KOC
 * 
 */
public class RealReturnMap extends ReturnMap {

	private Map<Integer, Integer> realMap;

	/**
	 * @param realMap
	 */
	public void setRealMap(Map<Integer, Integer> realMap) {
		this.realMap = realMap;
	}

	/**
	 * @param id
	 */
	public RealReturnMap(String id, Map<Integer, Integer> realMap) {
		super(id);
		this.realMap = realMap;
	}

	public RealReturnMap(Map<Integer, Integer> realMap) {
		this(UNKNOWN_ID, realMap);
	}

	public RealReturnMap() {
		this(UNKNOWN_ID, new HashMap<Integer, Integer>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		return realMap.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return realMap.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		return realMap.containsKey(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		return realMap.containsValue(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public Integer get(Object key) {
		return realMap.get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Integer put(Integer key, Integer value) {
		return realMap.put(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public Integer remove(Object key) {
		return realMap.remove(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends Integer, ? extends Integer> m) {
		realMap.putAll(m);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {
		realMap.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<Integer> keySet() {
		return realMap.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<Integer> values() {
		return realMap.values();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<Integer, Integer>> entrySet() {
		return realMap.entrySet();
	}

	@Override
	public void completeInternalProxy(String id, IWolfService realService) {
		// Nothing to do
	}

}
