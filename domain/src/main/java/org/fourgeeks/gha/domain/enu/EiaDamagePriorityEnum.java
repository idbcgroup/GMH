package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

public enum EiaDamagePriorityEnum {
	NORMAL("Normal"), HIGH("Alta");

	private String name;

	private EiaDamagePriorityEnum(String name) {
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
		for (EiaDamagePriorityEnum priority : EiaDamagePriorityEnum.values())
			valueMap.put(priority.name() + "", priority.toString());
		return valueMap;
	}

}
