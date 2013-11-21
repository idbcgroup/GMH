package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

public enum MaintenancePlanificationState {
	ACTIVE("Activo"), INACTIVE("Inactivo");

	private String name;

	private MaintenancePlanificationState(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenancePlanificationState val : MaintenancePlanificationState.values())
			valueMap.put(val.name() + "", val.toString());
		return valueMap;
	}
}
