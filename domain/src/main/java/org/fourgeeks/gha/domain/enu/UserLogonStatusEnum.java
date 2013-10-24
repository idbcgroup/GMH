package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

public enum UserLogonStatusEnum {

	LOGOFF, LOGON, STAYOUT, STAYIN, BLOCKED;

	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (UserLogonStatusEnum localEnum : UserLogonStatusEnum.values())
			valueMap.put(localEnum.name() + "", localEnum.toString());
		return valueMap;
	}

	// TODO: ponerle los String

}
