package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

public enum EiaDamageStatusEnum {
	FAILURE("Equipo con Falla"), DAMAGE("Equipo Dañado");

	private String name;

	private EiaDamageStatusEnum(String name) {
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
		for (EiaDamageStatusEnum status : EiaDamageStatusEnum.values())
			valueMap.put(status.name() + "", status.toString());
		return valueMap;
	}
}
