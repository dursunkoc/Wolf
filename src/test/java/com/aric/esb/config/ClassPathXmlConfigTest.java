/**
 * 
 */
package com.aric.esb.config;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.aric.esb.IWolfService;
import com.aric.esb.WolfService;
import com.aric.esb.db.DBFunctionCaller;
import com.aric.esb.db.DBProcedureCaller;
import com.aric.esb.ejb.EjbCaller;
import com.aric.esb.resourcefactory.EjbResourceFactory;

/**
 * @author Dursun KOC
 * 
 */
public class ClassPathXmlConfigTest {

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
	}

	/**
	 * Test method for
	 * {@link com.aric.esb.config.WolfServiceConfig#WolfServiceConfig()}.
	 * 
	 * @throws SAXException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@SuppressWarnings("unused")
	@Test
	public void testClassPathXmlConfig() throws IOException, SAXException,
			URISyntaxException {
		ClassPathXmlConfig config = new ClassPathXmlConfig();
		final Config parseXmlConfig = config
				.parseXmlConfig("/SampleServiceDefinition.xml");
		final WolfService configureService = (WolfService) parseXmlConfig
				.configureService(new ConfigurationProcessTrace());

		final IWolfService service1 = configureService.getService("dbRetMap-1");
		final DBProcedureCaller service2 = (DBProcedureCaller)configureService.getService("dbProc-1");
		final DBProcedureCaller service3 = (DBProcedureCaller) configureService.getService("dbProc-2");
		final EjbResourceFactory service4 = (EjbResourceFactory) configureService.getService("ejbResourFac-1");
		final EjbCaller service5 = (EjbCaller) configureService.getService("ejbCAller-2");
		final DBFunctionCaller service6 = (DBFunctionCaller) configureService.getService("dbFunction-1");
		final EjbCaller service7 = (EjbCaller)configureService.getService("ejbCAller-1");
		final IWolfService service8 = configureService.getService("dbRetMap-2");
		final IWolfService service9 = configureService.getService("dbResFac-1");
		final DBFunctionCaller service10 = (DBFunctionCaller) configureService.getService("dbFunction-2");

	}

}
