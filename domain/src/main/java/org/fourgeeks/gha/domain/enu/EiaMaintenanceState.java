package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

public enum EiaMaintenanceState {
	ACCOMPLISHED("Realizado"), CANCELED("Cancelado"), DEFERRED("Diferido"), DAMAGE(
			"Dañado"), REPARED("Reparado");

	private String name;

	private EiaMaintenanceState(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (EiaMaintenanceState val : EiaMaintenanceState.values())
			valueMap.put(val.name() + "", val.toString());
		return valueMap;
	}

	public static LinkedHashMap<String, String> toValueMap(
			EiaMaintenanceState... statuses) {

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (EiaMaintenanceState val : statuses)
			valueMap.put(val.name() + "", val.toString());
		return valueMap;
	}
}
