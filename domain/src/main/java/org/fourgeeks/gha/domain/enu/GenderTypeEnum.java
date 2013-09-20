/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * @author emiliot
 * 
 */
public enum GenderTypeEnum implements Serializable {
	/**
	 * 
	 */
	MALE("Masculino"), /**
	 * 
	 */
	FEMALE("Femenino");

	private String name;

	GenderTypeEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	/**
	 * @param string
	 * @return return the enum represented by this string or null
	 */
	public static GenderTypeEnum getByString(String string) {
		if (string == null)
			return null;

		for (GenderTypeEnum e : GenderTypeEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (GenderTypeEnum genderType : GenderTypeEnum.values())
			valueMap.put(genderType.name() + "", genderType.toString());
		return valueMap;
	}

	// public static ArrayList<String> getFormItems() {
	// ArrayList<String> list = new ArrayList<String>();
	// for (GenderTypeEnum genderType : GenderTypeEnum.values())
	// list.add(genderType.toString());
	// return list;
	// }
}
