/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author alacret
 */
public enum MaintenancePlanType {
	PREVENTIVE, OVERHAUL;

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenancePlanType documentType : MaintenancePlanType.values())
			valueMap.put(documentType.name() + "", documentType.toString());
		return valueMap;
	}
}
