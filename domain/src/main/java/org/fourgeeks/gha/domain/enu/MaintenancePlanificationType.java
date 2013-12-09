package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

public enum MaintenancePlanificationType {
	PREVENTIVE("Preventivo"), CORRECTIVE("Correctivo");

	private String name;

	private MaintenancePlanificationType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenancePlanificationType type : MaintenancePlanificationType.values())
			valueMap.put(type.name() + "", type.toString());
		return valueMap;
	}
}
