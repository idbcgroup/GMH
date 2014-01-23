package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author naramirez
 */
public enum ActivityCategoryEnum {
	/** Mantenimiento */
	MAINTENANCE,
	/** Logistica */
	LOGISTIC,
	/** Operaciones */
	OPERATIONS,
	/** Administrativa */
	ADMINISTRATIVE,
	/** Del Sistema */
	SYSTEM;

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (ActivityCategoryEnum value : ActivityCategoryEnum.values()) {
			valueMap.put(value.name() + "", value.toString());
		}

		return valueMap;
	}
}
