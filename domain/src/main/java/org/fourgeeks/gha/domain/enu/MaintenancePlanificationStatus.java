package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

public enum MaintenancePlanificationStatus {
	ASOCIATED("Asociado"), PLANIFICATED("Planificado"), INICIATED("Iniciado"), CANCELED(
			"Cancelado"), INACTIVE("Inactivo");

	private String name;

	private MaintenancePlanificationStatus(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenancePlanificationStatus val : MaintenancePlanificationStatus
				.values())
			valueMap.put(val.name() + "", val.toString());
		return valueMap;
	}
}
