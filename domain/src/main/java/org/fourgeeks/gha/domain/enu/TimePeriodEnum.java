/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author alacret
 * 
 */
public enum TimePeriodEnum {
	HOURS("hours"), DAYS("days"), WEEKS("weeks"), MONTHS("months"), SEMESTERS(
			"semesters"), YEARS("years");

	private String name;

	TimePeriodEnum(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
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
