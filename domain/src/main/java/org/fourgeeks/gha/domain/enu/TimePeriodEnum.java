/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author emiliot
 *
 */
public enum TimePeriodEnum {
	HOURS("Horas"),
	DAYS("Días"),
	WEEKS("Semanas"),
	MONTHS("Meses"),
	SEMESTERS("Semestres"),
	YEARS("Años");
	
	private String name;
	
	TimePeriodEnum(String name) {
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
	public static TimePeriodEnum getByString(String string) {
		if (string == null)
			return null;

		for (TimePeriodEnum e : TimePeriodEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}
	
	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (TimePeriodEnum timePeriod : TimePeriodEnum.values())
			valueMap.put(timePeriod.name() + "", timePeriod.toString());
		return valueMap;
	}
}
