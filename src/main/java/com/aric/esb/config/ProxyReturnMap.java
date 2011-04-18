/**
 * 
 */
package com.aric.esb.config;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.aric.esb.IWolfService;

/**
 * @author Dursun KOC
 * 
 */
public class ProxyReturnMap extends ReturnMap {
	private RealReturnMap realReturnMap;

	/**
	 * @param realReturnMap
	 */
	public void setRealReturnMap(RealReturnMap realReturnMap) {
		this.realReturnMap = realReturnMap;
	}

	/**
	 * @param id
	 */
	public ProxyReturnMap(String id) {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		return realReturnMap.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return realReturnMap.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		return realReturnMap.containsKey(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		return realReturnMap.containsValue(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public Integer get(Object key) {
		return realReturnMap.get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Integer put(Integer key, Integer value) {
		return realReturnMap.put(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public Integer remove(Object key) {
		return realReturnMap.remove(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends Integer, ? extends Integer> m) {
		realReturnMap.putAll(m);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {
		realReturnMap.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<Integer> keySet() {
		return realReturnMap.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<Integer> values() {
		return realReturnMap.values();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<Integer, Integer>> entrySet() {
		return realReturnMap.entrySet();
	}

	@Override
	public void completeInternalProxy(String id, IWolfService realService) {
		//Nothing to do
	}

}
