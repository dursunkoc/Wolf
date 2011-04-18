/**
 * 
 */
package com.aric.esb.environment;

import java.util.HashMap;
import java.util.Map;

import com.aric.esb.exceptions.BindVariableDoesNotExists;
import com.aric.esb.exceptions.VariableAlreadyExistsException;
import com.aric.esb.exceptions.VariableDoesNotExistException;
import com.aric.esb.util.StringUtils;

/**
 * @author Dursun KOC
 * 
 */
public class VariableEnvironment {
	private Map<String, Variable> env;

	public Map<String, Variable> getEnv() {
		return env;
	}

	public VariableEnvironment() {
		env = new HashMap<String, Variable>();
	}

	public VariableEnvironment(Map<String, Variable> env) {
		this.env = env;
	}

	public void addVariable(String name, Variable variable) {
		if (isVariableExists(name)) {
			assertVariableAlreadyExists(name);
		}
		env.put(name, variable);
	}

	public void addOrUpdateVariable(String name, Variable variable) {
		env.put(name, variable);
	}

	public void UpdateVariable(String name, Variable variable)
			throws VariableDoesNotExistException {
		if (!isVariableExists(name)) {
			assertVariableDoesNotExist(name);
		}
		env.put(name, variable);
	}

	public boolean isVariableExists(String name) {
		return env.containsKey(name);
	}

	public Variable getCheckedVariable(String name)
			throws VariableDoesNotExistException {
		if (!isVariableExists(name)) {
			assertVariableDoesNotExist(name);
		}
		return env.get(name);
	}

	public Variable getUncheckedVariable(String name) {
		if (!isVariableExists(name)) {
			return NullVariable.getInstance();
		}
		return env.get(name);
	}

	private void assertVariableAlreadyExists(String name) {
		throw new VariableAlreadyExistsException(
				"A variable already exists named:\"" + name + "\"");
	}

	private void assertVariableDoesNotExist(String name)
			throws VariableDoesNotExistException {
		throw new VariableDoesNotExistException("No variable exists named:\""
				+ name + "\"");
	}

	public String replaceVariables(String str) throws BindVariableDoesNotExists {
		return StringUtils.replaceMappedVariables(str, env);
	}

}
