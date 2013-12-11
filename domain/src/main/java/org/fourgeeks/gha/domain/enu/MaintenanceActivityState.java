/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author naramirez
 */
public enum MaintenanceActivityState {
	/** maintenance activity state: Activo */
	ACTIVE,
	/** maintenance activity state: Inactivo */
	INACTIVE;

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenanceActivityState value : MaintenanceActivityState.values())
			valueMap.put(value.name() + "", value.toString());
		return valueMap;
	}
}
