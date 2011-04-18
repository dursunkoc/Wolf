/**
 * 
 */
package com.aric.esb.config;

import org.apache.commons.digester.Digester;

import com.aric.esb.db.DBProcedureCaller;
import com.aric.esb.exceptions.CallerException;
import com.aric.esb.resourcefactory.ResourceFactory;

/**
 * @author Dursun KOC
 * 
 */
public class DBProcedureCallerConfig extends DBCallerConfig {

	private static final String ADD_DB_PROCEDURE_CALLER_CONFIG = "addDBProcedureCallerConfig";
	private static final String _DBPROCEDURECALLER_TAG = "DBProcedureCaller";

	/**
	 * @param parent
	 * @param digester
	 */
	public static void registerDigester(String parent, Digester digester) {
		final String myPath = parent + _SEP + _DBPROCEDURECALLER_TAG;
		final String[] properties = new String[] { "id", "dbMethodName",
				"connectionJndiName", "resourceFactoryId", "returnMapId" };

		digester.addObjectCreate(myPath, DBProcedureCallerConfig.class);
		digester.addSetNext(myPath, ADD_DB_PROCEDURE_CALLER_CONFIG);
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
	protected DBProcedureCaller prepareCaller(String methodName,
			ReturnMap returnMap, ResourceFactory resourceFactory,
			String connectionName) throws CallerException {
		DBProcedureCaller caller = new DBProcedureCaller(methodName, returnMap,
				resourceFactory, connectionName);
		caller.setId(this.id);
		return caller;
	}

}
