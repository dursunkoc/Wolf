/**
 * 
 */
package com.aric.esb.config;

import java.sql.Types;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.digester.Digester;

import com.aric.esb.IWolfService;
import com.aric.esb.exceptions.ConfigurationException;

/**
 * @author Dursun KOC
 * 
 */
public class DBReturnMapConfig implements Config {
	private static final String _DBRETURNMAP_TAG = "DBReturnMap";
	private static final String ADD_DBRETURNMAP = "addDBReturnMapConfig";

	private String id;
	private Set<DBReturnMapEntryConfig> dbReturnMapEntryConfigs;

	public DBReturnMapConfig() {
		this.dbReturnMapEntryConfigs = new HashSet<DBReturnMapEntryConfig>();
	}

	public static void registerDigester(String parent, Digester digester) {

		final String myPath = parent + _SEP + _DBRETURNMAP_TAG;
		final String[] properties = new String[] { "id" };
		digester.addObjectCreate(myPath, DBReturnMapConfig.class);
		digester.addSetNext(myPath, ADD_DBRETURNMAP);
		digester.addSetProperties(myPath, properties, properties);
		DBReturnMapEntryConfig.registerDigester(myPath, digester);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<DBReturnMapEntryConfig> getDbReturnMapEntryConfigs() {
		return dbReturnMapEntryConfigs;
	}

	public void setDbReturnMapEntryConfigs(
			Set<DBReturnMapEntryConfig> dbReturnMapEntryConfigs) {
		this.dbReturnMapEntryConfigs = dbReturnMapEntryConfigs;
	}

	public void addDbReturnMapEntryConfig(
			DBReturnMapEntryConfig dbReturnMapEntryConfig) {
		this.dbReturnMapEntryConfigs.add(dbReturnMapEntryConfig);
	}

	/**
	 * @param mapEntryConfig
	 * @return
	 */
	private Integer convertoSqlType(DBReturnMapEntryConfig mapEntryConfig) {
		try {
			final Integer typeId = Types.class.getDeclaredField(
					mapEntryConfig.getType()).getInt(Integer.class);
			return typeId;
		} catch (Exception e) {
			throw new ConfigurationException("No such sql type exist: '"
					+ mapEntryConfig.getType() + "'");
		}
	}

	@Override
	public IWolfService configureService(ConfigurationProcessTrace trace) {
		RealReturnMap map = new RealReturnMap(this.id,new HashMap<Integer, Integer>());
		for (DBReturnMapEntryConfig mapEntryConfig : dbReturnMapEntryConfigs) {
			final int index = mapEntryConfig.getIndex();
			final int typeId = convertoSqlType(mapEntryConfig);
			map.put(index, typeId);
		}
		return map;
	}

}
