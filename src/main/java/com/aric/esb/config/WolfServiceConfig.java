/**
 * 
 */
package com.aric.esb.config;

import java.util.HashSet;
import java.util.Set;

import com.aric.esb.IWolfService;
import com.aric.esb.WolfService;

/**
 * @author Dursun KOC
 * 
 */
public class WolfServiceConfig implements Config {
	private Set<DBFunctionCallerConfig> dbFunctionCallerConfigs;
	private Set<DBProcedureCallerConfig> dbProcedureCallerConfigs;
	private Set<WSCallerConfig> wsCallerConfigs;
	private Set<EJBCallerConfig> ejbCallerConfigs;
	private Set<DBResourceFactoryConfig> dbResourceFactoryConfigs;
	private Set<EjbResourceFactoryConfig> ejbResourceFactoryConfigs;
	private Set<DBReturnMapConfig> dbReturnMapConfigs;

	/**
	 * 
	 */
	public WolfServiceConfig() {
		this.dbFunctionCallerConfigs = new HashSet<DBFunctionCallerConfig>();
		this.dbProcedureCallerConfigs = new HashSet<DBProcedureCallerConfig>();
		this.wsCallerConfigs = new HashSet<WSCallerConfig>();
		this.ejbCallerConfigs = new HashSet<EJBCallerConfig>();
		this.dbReturnMapConfigs = new HashSet<DBReturnMapConfig>();
		this.dbResourceFactoryConfigs = new HashSet<DBResourceFactoryConfig>();
		this.ejbResourceFactoryConfigs = new HashSet<EjbResourceFactoryConfig>();
	}

	/**
	 * @param dbFunctionCallerConfig
	 */
	public void addDBFunctionCallerConfig(
			DBFunctionCallerConfig dbFunctionCallerConfig) {
		this.dbFunctionCallerConfigs.add(dbFunctionCallerConfig);
	}

	/**
	 * @param dbProcedureCallerConfig
	 */
	public void addDBProcedureCallerConfig(
			DBProcedureCallerConfig dbProcedureCallerConfig) {
		this.dbProcedureCallerConfigs.add(dbProcedureCallerConfig);
	}

	/**
	 * @param wsCallerConfig
	 */
	public void addWSCallerConfig(WSCallerConfig wsCallerConfig) {
		this.wsCallerConfigs.add(wsCallerConfig);
	}

	/**
	 * @param ejbCallerConfig
	 */
	public void addEjbCallerConfig(EJBCallerConfig ejbCallerConfig) {
		this.ejbCallerConfigs.add(ejbCallerConfig);
	}

	/**
	 * @param dbResourceFactoryConfig
	 */
	public void addDBResourceFactoryConfig(
			DBResourceFactoryConfig dbResourceFactoryConfig) {
		this.dbResourceFactoryConfigs.add(dbResourceFactoryConfig);
	}

	/**
	 * @param ejbResourceFactoryConfig
	 */
	public void addEjbResourceFactoryConfig(
			EjbResourceFactoryConfig ejbResourceFactoryConfig) {
		this.ejbResourceFactoryConfigs.add(ejbResourceFactoryConfig);
	}

	/**
	 * @param dbReturnMapConfig
	 */
	public void addDBReturnMapConfig(DBReturnMapConfig dbReturnMapConfig) {
		this.dbReturnMapConfigs.add(dbReturnMapConfig);
	}

	/**
	 * @return
	 */
	public Set<DBFunctionCallerConfig> getDbFunctionCallerConfigs() {
		return dbFunctionCallerConfigs;
	}

	/**
	 * @return
	 */
	public Set<DBProcedureCallerConfig> getDbProcedureCallerConfigs() {
		return dbProcedureCallerConfigs;
	}

	/**
	 * @return
	 */
	public Set<DBResourceFactoryConfig> getDbResourceFactoryConfigs() {
		return dbResourceFactoryConfigs;
	}

	/**
	 * @return
	 */
	public Set<DBReturnMapConfig> getDbReturnMapConfigs() {
		return dbReturnMapConfigs;
	}

	/**
	 * @return
	 */
	public Set<EJBCallerConfig> getEjbCallerConfigs() {
		return ejbCallerConfigs;
	}

	/**
	 * @return
	 */
	public Set<EjbResourceFactoryConfig> getEjbResourceFactoryConfigs() {
		return ejbResourceFactoryConfigs;
	}

	/**
	 * @return
	 */
	public Set<WSCallerConfig> getWsCallerConfigs() {
		return wsCallerConfigs;
	}

	@Override
	public WolfService configureService(ConfigurationProcessTrace trace) {
		WolfService wolfService = new WolfService();
		Set<Config> allServices = new HashSet<Config>();
		allServices.addAll(this.dbFunctionCallerConfigs);
		allServices.addAll(this.dbProcedureCallerConfigs);
		allServices.addAll(this.dbResourceFactoryConfigs);
		allServices.addAll(this.dbReturnMapConfigs);
		allServices.addAll(this.ejbCallerConfigs);
		allServices.addAll(this.ejbResourceFactoryConfigs);
		allServices.addAll(this.wsCallerConfigs);

		for (Config config : allServices) {
			final IWolfService service = config.configureService(trace);
			wolfService.addService(service);
		}
		trace.completeReferenceDefinitions(wolfService);
		return wolfService;
	}
}
