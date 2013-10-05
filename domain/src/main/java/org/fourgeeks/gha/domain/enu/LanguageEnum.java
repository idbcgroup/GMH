/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author emiliot
 * 
 */
public enum LanguageEnum {
	/**
	 * 
	 */
	ES,
	/**
	 * 
	 */
	EN;

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (LanguageEnum language : LanguageEnum.values())
			valueMap.put(language.name() + "", language.toString());
		return valueMap;
	}
}
