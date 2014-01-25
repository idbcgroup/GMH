/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author naramirez
 * 
 */
public enum ActivitySubCategoryEnum {
	/** maintenance activity sub-category or sub-type: Calibración */
	CALIBRATION;

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (ActivitySubCategoryEnum value : ActivitySubCategoryEnum
				.values()) {
			valueMap.put(value.name() + "", value.toString());
		}

		return valueMap;
	}
}
