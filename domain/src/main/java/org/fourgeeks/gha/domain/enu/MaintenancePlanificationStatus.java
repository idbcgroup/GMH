package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

public enum MaintenancePlanificationStatus {
	ACCOMPLISHED("Realizado"), CANCELED("Cancelado"), DEFERRED("Diferido"), EIA_DAMAGE(
			"Daño Equipo");

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

	public static LinkedHashMap<String, String> toValueMap(
			MaintenancePlanificationStatus... statuses) {

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenancePlanificationStatus val : statuses)
			valueMap.put(val.name() + "", val.toString());
		return valueMap;
	}
}
