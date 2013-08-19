/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import java.util.LinkedHashMap;

/**
 * @author emiliot
 * 
 */
public enum MaterialTypeEnum {
	MATERIAL("Material"), SERVICIO("Servicio"), UTILITARIO("Utilitario");

	private String name;

	MaterialTypeEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	// /**
	// * @param string
	// * @return the MaterialTypeEnum representated by this value, or null if it
	// * does not exist
	// */
	// public static MaterialTypeEnum getByString(String string) {
	// if (string == null)
	// return null;
	//
	// for (MaterialTypeEnum e : MaterialTypeEnum.values())
	// if (string.equals(e.name()))
	// return e;
	//
	// return null;
	// }

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaterialTypeEnum e : MaterialTypeEnum.values())
			valueMap.put(e.name() + "", e.toString());
		return valueMap;
	}
}
