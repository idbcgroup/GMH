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
	ES("Español"), EN("English");

	private String name;

	LanguageEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	/**
	 * @param string
	 * @return return the enum represented by this string or null
	 */
	public static LanguageEnum getByString(String string) {
		if (string == null)
			return null;

		for (LanguageEnum e : LanguageEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

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
