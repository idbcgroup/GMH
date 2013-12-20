package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author naramirez
 */
public enum MaintenanceActivityTypeEnum {
	/** maintenance activity category or type: Mantenimiento */
	MAINTENANCE;

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenanceActivityTypeEnum value : MaintenanceActivityTypeEnum
				.values()) {
			valueMap.put(value.name() + "", value.toString());
		}

		return valueMap;
	}
}
