/**
 * @author emiliot
 *
 */
package org.fourgeeks.gha.domain.enu;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author emiliot
 * 
 */
public enum CodeTypeEnum {
	NUMERIC("numeric", "codetype-numeric"), ALPHANUMERIC("alphanumeric",
			"codetype-alphanumeric"), BARCODE("barcode", "codetype-barcodes");
	/**
	 * @param string
	 * @return the CodeTypeEnum representated by this value, or null if it does
	 *         not exist
	 */
	public static CodeTypeEnum getByString(String string) {
		if (string == null)
			return null;

		for (CodeTypeEnum e : CodeTypeEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	public static String getUiKey(CodeTypeEnum state) {
		return state.uiKey;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (CodeTypeEnum type : CodeTypeEnum.values())
			valueMap.put(type.name() + "", type.toString());
		return valueMap;
	}

	/**
	 * @return a {@link List} of {@link EiaStateEnum}
	 */
	public static List<CodeTypeEnum> valuesList() {
		return Arrays.asList(CodeTypeEnum.values());
	}

	private String name;

	private String uiKey;

	/**
	 * 
	 */
	CodeTypeEnum(String name, String uiKey) {
		this.name = name;
		this.uiKey = uiKey;
	}
}
