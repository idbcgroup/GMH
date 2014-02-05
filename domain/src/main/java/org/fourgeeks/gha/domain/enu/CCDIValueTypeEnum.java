package org.fourgeeks.gha.domain.enu;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author emiliot
 * 
 */
public enum CCDIValueTypeEnum {
	FIXED("Fixed", "ccdi-valuetype-fixed"), TEXT("Text", "ccdi-valuetype-text"), VARIABLE(
			"Variable", "ccdi-valuetype-variable");

	/**
	 * @param string
	 * @return the CCDIValueTypeEnum representated by this value, or null if it
	 *         does not exist
	 */
	public static CCDIValueTypeEnum getByString(String string) {
		if (string == null)
			return null;

		for (CCDIValueTypeEnum e : CCDIValueTypeEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	public static String getUiKey(CCDIValueTypeEnum type) {
		return type.uiKey;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (CCDIValueTypeEnum type : CCDIValueTypeEnum.values())
			valueMap.put(type.name() + "", type.toString());
		return valueMap;
	}

	/**
	 * @return a {@link List} of {@link EiaStateEnum}
	 */
	public static List<CCDIValueTypeEnum> valuesList() {
		return Arrays.asList(CCDIValueTypeEnum.values());
	}

	private String name;

	private String uiKey;

	/**
	 * 
	 */
	CCDIValueTypeEnum(String name, String uiKey) {
		this.name = name;
		this.uiKey = uiKey;
	}
}
