/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author emiliot
 * 
 */
public enum EiaTypeEnum {
	EQUIPMENT("Equipo"), INSTALLATION("Instalación"), PART("Parte"), REPLACEMENT(
			"Repuesto");

	private String name;

	EiaTypeEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	/**
	 * @param string
	 * @return
	 */
	public static EiaTypeEnum getByString(String string) {
		if (string == null)
			return null;

		for (EiaTypeEnum e : EiaTypeEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (EiaTypeEnum e : EiaTypeEnum.values())
			valueMap.put(e.name() + "", e.toString());
		return valueMap;
	}
}
