/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author alacret
 */
public enum MaintenancePlanState {
	ACTIVE, INACTIVE;

	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenancePlanState documentType : MaintenancePlanState.values())
			valueMap.put(documentType.name() + "", documentType.toString());
		return valueMap;
	}
}
