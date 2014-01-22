package org.fourgeeks.gha.domain.enu;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author emiliot
 * 
 */
public enum CCDIStatusEnum {
	ACTIVE("activo", "ccdistatus-active"), INACTIVE("inactive",
			"ccdistatus-inactive");
	/**
	 * @param string
	 * @return the CodeTypeEnum representated by this value, or null if it does
	 *         not exist
	 */
	public static CCDIStatusEnum getByString(String string) {
		if (string == null)
			return null;

		for (CCDIStatusEnum e : CCDIStatusEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	public static String getUiKey(CCDIStatusEnum state) {
		return state.uiKey;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (CCDIStatusEnum type : CCDIStatusEnum.values())
			valueMap.put(type.name() + "", type.toString());
		return valueMap;
	}

	/**
	 * @return a {@link List} of {@link EiaStateEnum}
	 */
	public static List<CCDIStatusEnum> valuesList() {
		return Arrays.asList(CCDIStatusEnum.values());
	}

	private String name;

	private String uiKey;

	/**
	 * 
	 */
	CCDIStatusEnum(String name, String uiKey) {
		this.name = name;
		this.uiKey = uiKey;
	}
}
