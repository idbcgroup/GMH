package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

public enum EiaReportFiltersEnum {
	/***/
	EDO_EQUIPO("Estado del Equipo"),
	/***/
	FACILITY("Facilidad"),
	/***/
	WORKING_AREA("Area de trabajo"),

	SIN_FILTRO("");

	private String nombre;

	EiaReportFiltersEnum(String name) {
		this.nombre = name;
	}

	@Override
	public String toString() {
		return nombre;
	}

	/**
	 * @param string
	 * @return the EiaReportFiltersEnum representated by this value, or null if
	 *         it does not exist
	 */
	public static EiaReportFiltersEnum getByString(String string) {
		if (string == null)
			return null;

		for (EiaReportFiltersEnum e : EiaReportFiltersEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (EiaReportFiltersEnum e : EiaReportFiltersEnum.values())
			valueMap.put(e.name(), e.toString());
		return valueMap;
	}
}
