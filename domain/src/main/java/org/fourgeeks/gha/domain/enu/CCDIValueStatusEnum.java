package org.fourgeeks.gha.domain.enu;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author emiliot
 * 
 */
public enum CCDIValueStatusEnum {
	ACTIVE("Activo", "ccdi-valuestatusenum-active"), INACTIVE("Inactivo",
			"ccdi-valuestatusenum-inactive");
	/**
	 * @param string
	 * @return the CCDIValueStatusEnum representated by this value, or null if
	 *         it does not exist
	 */
	public static CCDIValueStatusEnum getByString(String string) {
		if (string == null)
			return null;

		for (CCDIValueStatusEnum e : CCDIValueStatusEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	public static String getUiKey(CCDIValueStatusEnum state) {
		return state.uiKey;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (CCDIValueStatusEnum status : CCDIValueStatusEnum.values())
			valueMap.put(status.name() + "", status.toString());
		return valueMap;
	}

	/**
	 * @return a {@link List} of {@link EiaStateEnum}
	 */
	public static List<CCDIValueStatusEnum> valuesList() {
		return Arrays.asList(CCDIValueStatusEnum.values());
	}

	private String name;

	private String uiKey;

	/**
	 * 
	 */
	CCDIValueStatusEnum(String name, String uiKey) {
		this.name = name;
		this.uiKey = uiKey;
	}
}
