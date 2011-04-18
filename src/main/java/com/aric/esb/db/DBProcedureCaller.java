/**
 * 
 */
package com.aric.esb.db;

import java.sql.CallableStatement;
import java.sql.SQLException;

import com.aric.esb.ArgumentBundle;
import com.aric.esb.config.ReturnMap;
import com.aric.esb.exceptions.CallerException;
import com.aric.esb.resourcefactory.ResourceFactory;

/**
 * @author Dursun KOC
 * 
 */
public class DBProcedureCaller extends DBCaller {
	private String id;

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.esb.IWolfService#isMe(java.lang.String)
	 */
	@Override
	public boolean isMe(String id) {
		if (this.id == null) {
			return false;
		}
		return this.id.equals(id);
	}

	/**
	 * @param dbMethodName
	 * @param returnMap
	 * @param resourceFactory
	 * @param connectionName
	 * @throws CallerException
	 */
	public DBProcedureCaller(String dbMethodName, ReturnMap returnMap,
			ResourceFactory resourceFactory, String connectionName)
			throws CallerException {
		super(dbMethodName, returnMap, resourceFactory, connectionName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.esb.db.DBCaller#bindArguments(com.aric.esb.ArgumentBundle,
	 * java.sql.CallableStatement)
	 */
	@Override
	protected void bindArguments(ArgumentBundle argumentBundle,
			CallableStatement callStmt) throws SQLException {
		for (int i = 0; i < argumentBundle.size(); i++) {
			callStmt.setObject(i + 1, argumentBundle.marshallArguments()[i]);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.esb.db.DBCaller#generateFunctionCallBody(int)
	 */
	@Override
	protected String generateFunctionCallBody(int size) throws CallerException {
		StringBuffer callBody = new StringBuffer();
		callBody.append("{ call ");
		callBody.append(this.getDbMethodName());
		callBody.append("(");
		for (int i = 0; i < size; i++) {
			callBody.append("?,");
		}
		if (callBody.indexOf("?,") > 0) {
			callBody.deleteCharAt(callBody.lastIndexOf(","));
		}
		callBody.append(")");
		callBody.append(" }");
		return callBody.toString();
	}

}
