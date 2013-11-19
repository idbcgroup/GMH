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
	HOURS, DAYS, WEEKS, MONTHS, SEMESTERS, YEARS;

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
