/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author alacret
 * 
 */
public enum ItSystemEnum {
	/**
	 * 
	 */
	PERIPHERAL("Periférico"), /**
	 * 
	 */
	COMPUTER("Computadora");

	private String name;

	ItSystemEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	/**
	 * @param string
	 * @return return the enum represented by this string or null
	 */
	public static ItSystemEnum getByString(String string) {
		if (string == null)
			return null;

		for (ItSystemEnum e : ItSystemEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (ItSystemEnum mobility : ItSystemEnum.values())
			valueMap.put(mobility.name() + "", mobility.toString());
		return valueMap;
	}
}
