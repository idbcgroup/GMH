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
	MATERIAL, SERVICIO, UTILITARIO;

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
