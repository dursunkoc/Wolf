/**
 * 
 */
package com.aric.esb.config;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 * @author Dursun KOC
 * 
 */
public class ClassPathXmlConfig {
	private static Logger logger = Logger.getLogger(ClassPathXmlConfig.class);

	/**
	 * @param pathToFile
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws URISyntaxException
	 */
	public Config parseXmlConfig(String pathToFile) throws IOException,
			SAXException, URISyntaxException {
		Digester digester = new Digester();
		digester.setValidating(false);

		final String parent = "WolfService";
		digester.addObjectCreate(parent, WolfServiceConfig.class);

		DBFunctionCallerConfig.registerDigester(parent, digester);
		DBProcedureCallerConfig.registerDigester(parent, digester);
		WSCallerConfig.registerDigester(parent, digester);
		EJBCallerConfig.registerDigester(parent, digester);
		DBResourceFactoryConfig.registerDigester(parent, digester);
		EjbResourceFactoryConfig.registerDigester(parent, digester);
		DBReturnMapConfig.registerDigester(parent, digester);

		final URL configResourceUrl = getClass().getResource(pathToFile);
		if (configResourceUrl == null) {
			logger.warn("No Resource found at pathToFile: " + pathToFile);
		}
		logger.info("Configuration file found at pathToFile: "
				+ configResourceUrl.toURI().getRawPath());
		File configurationFile = new File(configResourceUrl.toURI());
		WolfServiceConfig wolfServiceConfig = (WolfServiceConfig) digester
				.parse(configurationFile);
		return wolfServiceConfig;
	}

}
