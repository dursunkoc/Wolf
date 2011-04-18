/**
 * 
 */
package com.aric.esb.db;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aric.esb.ArgumentBundle;
import com.aric.esb.arguments.Argument;
import com.aric.esb.arguments.SimpleArgument;
import com.aric.esb.config.RealReturnMap;
import com.aric.esb.config.ReturnMap;
import com.aric.esb.exceptions.CallerException;
import com.aric.esb.resourcefactory.DBResourceFactory;
import com.aric.testutils.JndiCreator;

/**
 * @author Dursun KOC
 * 
 */
public class DBCallerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		JndiCreator.kill();
	}

	/**
	 * Test method for
	 * {@link com.aric.esb.db.DBCaller#call(com.aric.esb.ArgumentBundle)}.
	 * 
	 * @throws CallerException
	 * @throws NamingException
	 */
	@Test
	public void testCallForOracle() throws Exception {
		JndiCreator.create(JndiCreator.DB_OPTION_ORACLE);
		String connectionName = "java:MyApp/myDS";
		Context context = new InitialContext();
		Properties properties = new Properties();
		properties.putAll(context.getEnvironment());
		DBResourceFactory resourceFactory = new DBResourceFactory(properties);

		ReturnMap returnMapForFunc = new RealReturnMap();
		returnMapForFunc.put(1, java.sql.Types.VARCHAR);

		ReturnMap returnMapForProc = new RealReturnMap();
		returnMapForProc.put(1, java.sql.Types.VARCHAR);
		returnMapForProc.put(3, java.sql.Types.NUMERIC);

		/*
		 * CREATE OR REPLACE function COMET_CORE.getCampaignName(id number)
		 * return varchar
		 */
		// Call a function
		DBCaller caller = new DBFunctionCaller("comet_core.getCampaignName",
				returnMapForFunc, resourceFactory, connectionName);

		ArgumentBundle argumentBundle = new ArgumentBundle(
				new Argument[] { new SimpleArgument("id", 1) });
		Map<Integer, Object> resultOfFunc = caller.call(argumentBundle);
		System.out.println(resultOfFunc);
		/*
		 * CREATE OR REPLACE PROCEDURE COMET_CORE.createcampaign ( NAME IN OUT
		 * VARCHAR2, GROUPS IN VARCHAR2, ID OUT NUMBER)
		 */
		// call a procedure
		caller = new DBProcedureCaller("comet_core.createCampaign",
				returnMapForProc, resourceFactory, connectionName);
		ArgumentBundle argumentBundle2 = new ArgumentBundle(new Argument[] {
				new SimpleArgument("name", "My Campaign"),
				new SimpleArgument("groups", "My Campaign Group"),
				new SimpleArgument("id", 177) });
		Map<Integer, Object> resultOfProc = caller.call(argumentBundle2);
		System.out.println(resultOfProc);
	}

	/**
	 * Test method for
	 * {@link com.aric.esb.db.DBCaller#call(com.aric.esb.ArgumentBundle)}.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testCallForMysql() throws Exception {
		JndiCreator.create(JndiCreator.DB_OPTION_MYSQL);
		String connectionName = "java:MyApp/myDS";
		Context context = new InitialContext();
		Properties properties = new Properties();
		properties.putAll(context.getEnvironment());
		DBResourceFactory resourceFactory = new DBResourceFactory(properties);
		/*
		 * CREATE DEFINER=`root`@`localhost` FUNCTION `getCampaignName`(id
		 * int(11)) RETURNS varchar(45) CHARSET utf8
		 */
		ReturnMap returnMap = new RealReturnMap();
		returnMap.put(1, java.sql.Types.VARCHAR);

		DBCaller caller = new DBFunctionCaller("test.getCampaignName",
				returnMap, resourceFactory, connectionName);
		ArgumentBundle argumentBundle = new ArgumentBundle(
				new Argument[] { new SimpleArgument("id", 1) });
		Map<Integer, Object> resultForFunc = caller.call(argumentBundle);
		System.out.println(resultForFunc);

		/*
		 * CREATE DEFINER=`root`@`localhost` PROCEDURE `createCampaign` (inout
		 * name varchar(45), in startdate date, in enddate date, in isabstract
		 * int(1), out id int(11))
		 */
		ReturnMap returnMapForProc = new RealReturnMap();
		returnMapForProc.put(1, java.sql.Types.VARCHAR);
		returnMapForProc.put(5, java.sql.Types.NUMERIC);
		caller = new DBProcedureCaller("test.createCampaign", returnMapForProc,
				resourceFactory, connectionName);
		Map<Integer, Object> resultForProc = caller.call(new ArgumentBundle(
				new Argument[] { new SimpleArgument("name", "My Campaign"),
						new SimpleArgument("startDate", new Date()),
						new SimpleArgument("endDate", new Date(23, 12, 2012)),
						new SimpleArgument("isAbstract", 2),
						new SimpleArgument("id", 10) }));
		System.out.println(resultForProc);
	}

}
