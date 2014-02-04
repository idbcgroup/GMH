/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author emiliot
 * 
 */
public enum CurrencyTypeEnum {
	BS("Bolívares fuertes"), DOLLARS("Dólares");

	private String name;

	CurrencyTypeEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	/**
	 * @param string
	 * @return the TimePeriodEnum representated by this value, or null if it
	 *         does not exist
	 */
	public static CurrencyTypeEnum getByString(String string) {
		if (string == null)
			return null;

		for (CurrencyTypeEnum e : CurrencyTypeEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (CurrencyTypeEnum currency : CurrencyTypeEnum.values())
			valueMap.put(currency.name() + "", currency.toString());
		return valueMap;
	}
}
