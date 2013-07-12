/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author emiliot
 * 
 */
public enum EiaSubTypeEnum {
	LIFE_SUPPORT("Suporte de vida"), DIAGNOSE("Diágnostico"), MEDICATION(
			"Medicación"), IT_SYSTEM("Sistema IT");

	private String name;

	EiaSubTypeEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public static EiaSubTypeEnum getByString(String string) {
		if (string == null)
			return null;

		for (EiaSubTypeEnum e : EiaSubTypeEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (EiaSubTypeEnum e : EiaSubTypeEnum.values())
			valueMap.put(e.name() + "", e.toString());
		return valueMap;
	}
}