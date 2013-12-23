package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author naramirez
 */
public enum MaintenancePlanStatus {
	/** Asociado */
	ASOCIATED,
	/** Planificado */
	PLANIFICATED,
	/** Iniciado */
	INICIATED,
	/** Cancelado */
	CANCELED,
	/** Inactivo */
	INACTIVE;

	/**
	 * @return the value map
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenancePlanStatus val : MaintenancePlanStatus.values())
			valueMap.put(val.name() + "", val.toString());
		return valueMap;
	}
}
