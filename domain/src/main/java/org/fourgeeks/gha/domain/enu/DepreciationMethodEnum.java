/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author emiliot
 *
 */
public enum DepreciationMethodEnum {
	METHOD_2("Metodo 1"),
	METHOD_1("Metodo 2");
	
	private String name;
	
	DepreciationMethodEnum(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
	/**
	 * @param string
	 * @return the TimePeriodEnum representated by this value, or null if it does
	 *         not exist
	 */
	public static DepreciationMethodEnum getByString(String string) {
		if (string == null)
			return null;

		for (DepreciationMethodEnum e : DepreciationMethodEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}
	
	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (DepreciationMethodEnum depMethod : DepreciationMethodEnum.values())
			valueMap.put(depMethod.name() + "", depMethod.toString());
		return valueMap;
	}

}
