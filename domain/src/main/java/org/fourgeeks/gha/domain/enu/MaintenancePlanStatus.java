package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

public enum MaintenancePlanStatus {
	ASOCIATED("Asociado"), PLANIFICATED("Planificado"), INICIATED("Iniciado"), CANCELED(
			"Cancelado"), INACTIVE("Inactivo");

	private String name;

	private MaintenancePlanStatus(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenancePlanStatus val : MaintenancePlanStatus.values())
			valueMap.put(val.name() + "", val.toString());
		return valueMap;
	}
}
