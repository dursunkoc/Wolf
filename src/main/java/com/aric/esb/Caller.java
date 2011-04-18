package com.aric.esb;

import com.aric.esb.exceptions.CallerException;
/**
 * @author Dursun KOC
 *
 */
public interface Caller extends IWolfService{
	public Object call(ArgumentBundle argumentBundle)
			throws CallerException;
}
