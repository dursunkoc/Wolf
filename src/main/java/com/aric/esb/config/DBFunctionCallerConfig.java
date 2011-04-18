/**
 * 
 */
package com.aric.esb.config;

import org.apache.commons.digester.Digester;

import com.aric.esb.db.DBFunctionCaller;
import com.aric.esb.exceptions.CallerException;
import com.aric.esb.resourcefactory.ResourceFactory;

/**
 * @author Dursun KOC
 * 
 */
public class DBFunctionCallerConfig extends DBCallerConfig {
	private static final String ADD_DB_FUNCTION_CALLER_CONFIG = "addDBFunctionCallerConfig";
	private static final String _DBFUNCTIONCALLER_TAG = "DBFunctionCaller";

	/**
	 * @param parent
	 * @param digester
	 */
	public static void registerDigester(String parent, Digester digester) {
		final String myPath = parent + _SEP + _DBFUNCTIONCALLER_TAG;
		final String[] properties = new String[] { "id", "dbMethodName",
				"connectionJndiName", "resourceFactoryId", "returnMapId" };

		digester.addObjectCreate(myPath, DBFunctionCallerConfig.class);
		digester.addSetNext(myPath, ADD_DB_FUNCTION_CALLER_CONFIG);
		digester.addSetProperties(myPath, properties, properties);
		DBResourceFactoryConfig.registerDigester(myPath, digester);
		DBReturnMapConfig.registerDigester(myPath, digester);
	}

	/**
	 * @param methodName
	 * @param returnMap
	 * @param resourceFactory
	 * @param connectionName
	 * @return
	 * @throws CallerException
	 */
	@Override
	protected DBFunctionCaller prepareCaller(String methodName,
			ReturnMap returnMap, ResourceFactory resourceFactory,
			String connectionName) throws CallerException {
		DBFunctionCaller caller = new DBFunctionCaller(methodName, returnMap,
				resourceFactory, connectionName);
		caller.setId(this.id);
		return caller;
	}

}
