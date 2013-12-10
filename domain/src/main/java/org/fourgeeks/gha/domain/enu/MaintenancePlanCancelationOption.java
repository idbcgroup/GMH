package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

public enum MaintenancePlanCancelationOption {
	NOT_DEFERRABLE("No Postergable");
	// TODO faltan las otras opciones de cancelacion

	private String name;

	private MaintenancePlanCancelationOption(String name) {
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
		for (MaintenancePlanCancelationOption type : MaintenancePlanCancelationOption
				.values())
			valueMap.put(type.name() + "", type.toString());

		return valueMap;
	}
}
