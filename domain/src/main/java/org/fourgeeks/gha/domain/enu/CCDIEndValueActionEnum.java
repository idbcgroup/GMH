package org.fourgeeks.gha.domain.enu;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author emiliot This enum holds the actions to take when the final value in a
 *         consecutive generation hits the last member posible
 */
public enum CCDIEndValueActionEnum {
	USE_NEXT_LEVEL("Siguiente", "ccdi-valueatend-next"), RESTART("Reiniciar",
			"ccdi-valueatend-restart");
	/**
	 * @param string
	 * @return the CCDIEndValueActionEnum representated by this value, or null
	 *         if it does not exist
	 */
	public static CCDIEndValueActionEnum getByString(String string) {
		if (string == null)
			return null;

		for (CCDIEndValueActionEnum e : CCDIEndValueActionEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	public static String getUiKey(CCDIEndValueActionEnum type) {
		return type.uiKey;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (CCDIEndValueActionEnum action : CCDIEndValueActionEnum.values())
			valueMap.put(action.name() + "", action.toString());
		return valueMap;
	}

	/**
	 * @return a {@link List} of {@link EiaStateEnum}
	 */
	public static List<CCDIEndValueActionEnum> valuesList() {
		return Arrays.asList(CCDIEndValueActionEnum.values());
	}

	private String name;

	private String uiKey;

	/**
	 * 
	 */
	CCDIEndValueActionEnum(String name, String uiKey) {
		this.name = name;
		this.uiKey = uiKey;
	}
}
