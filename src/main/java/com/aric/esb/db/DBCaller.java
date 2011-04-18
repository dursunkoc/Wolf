package com.aric.esb.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.aric.esb.ArgumentBundle;
import com.aric.esb.Caller;
import com.aric.esb.IWolfService;
import com.aric.esb.config.ProxyResourceFactory;
import com.aric.esb.config.ProxyReturnMap;
import com.aric.esb.config.RealReturnMap;
import com.aric.esb.config.ReturnMap;
import com.aric.esb.exceptions.CallerException;
import com.aric.esb.exceptions.ResourceFactoryException;
import com.aric.esb.resourcefactory.ResourceFactory;

/**
 * 
 */

/**
 * @author Dursun KOC
 * 
 */
public abstract class DBCaller implements Caller {
	private String dbMethodName;
	// private Map<Integer, Integer> returnMap;
	private ReturnMap returnMap;
	// private Connection connection;
	private ResourceFactory resourceFactory;
	private String connectionName;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.esb.Caller#call(com.aric.esb.ArgumentBundle)
	 */
	@Override
	public Map<Integer, Object> call(ArgumentBundle argumentBundle)
			throws CallerException {
		try {
			int argumentSize = argumentBundle.size();
			String callBody = this.generateFunctionCallBody(argumentSize);
			Connection connection = getConnection();
			CallableStatement callStmt = connection.prepareCall(callBody);
			registerOutParameters(callStmt);
			bindArguments(argumentBundle, callStmt);
			callStmt.execute();
			Map<Integer, Object> mapOfReturn = extractOutput(callStmt);
			return mapOfReturn;
		} catch (Exception e) {
			throw new CallerException(e);
		}
	}

	/**
	 * @return
	 * @throws ResourceFactoryException
	 */
	private Connection getConnection() throws ResourceFactoryException {
		final Object resource = resourceFactory.getResource(connectionName);
		if (resource instanceof Connection) {
			Connection connection = (Connection) resource;
			return connection;
		}
		throw new ResourceFactoryException(
				"Retrieved unknown resource from resourceFactory. Expected java.sql.Connection");
	}

	/**
	 * @param callStmt
	 * @return
	 * @throws SQLException
	 */
	private Map<Integer, Object> extractOutput(CallableStatement callStmt)
			throws SQLException {
		Map<Integer, Object> mapOfReturn = new HashMap<Integer, Object>();
		Iterator<Integer> returnMapExt = returnMap.keySet().iterator();
		while (returnMapExt.hasNext()) {
			Integer returnIndex = returnMapExt.next();
			mapOfReturn.put(returnIndex, callStmt.getObject(returnIndex));
		}
		return mapOfReturn;
	}

	/**
	 * @param callStmt
	 * @throws SQLException
	 */
	private void registerOutParameters(CallableStatement callStmt)
			throws SQLException {
		Iterator<Integer> returnMapInit = returnMap.keySet().iterator();
		while (returnMapInit.hasNext()) {
			Integer returnIndex = returnMapInit.next();
			Integer returnType = returnMap.get(returnIndex);
			callStmt.registerOutParameter(returnIndex, returnType);
		}
	}

	@Override
	public void completeInternalProxy(String id, IWolfService realService) {
		if (this.resourceFactory.isMe(id)
				&& this.resourceFactory instanceof ProxyResourceFactory) {
			((ProxyResourceFactory) this.resourceFactory)
					.setRealFactory((ResourceFactory) realService);
		} else if (this.returnMap.isMe(id)
				&& this.returnMap instanceof ProxyReturnMap) {
			((ProxyReturnMap) this.returnMap)
					.setRealReturnMap((RealReturnMap) realService);
		}

	}

	protected abstract void bindArguments(ArgumentBundle argumentBundle,
			CallableStatement callStmt) throws SQLException;

	/**
	 * @param dbMethodName
	 * @param returnMap
	 * @param resourceFactory
	 * @param connectionName
	 * @throws CallerException
	 */
	public DBCaller(String dbMethodName, ReturnMap returnMap,
			ResourceFactory resourceFactory, String connectionName)
			throws CallerException {
		this.dbMethodName = dbMethodName;
		this.returnMap = returnMap;
		this.resourceFactory = resourceFactory;
		this.connectionName = connectionName;
	}

	/**
	 * @return
	 */
	protected String getDbMethodName() {
		return dbMethodName;
	}

	/**
	 * @param size
	 * @return
	 * @throws CallerException
	 */
	protected abstract String generateFunctionCallBody(int size)
			throws CallerException;

}
