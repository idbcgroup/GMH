package org.fourgeeks.gha.domain.enu;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public enum CredentialTypeEnum {
	/**
	 * 
	 */
	PHYSICIAN("Médico"), /**
	 * 
	 */
	NURSE("Enfermera"), /**
	 * 
	 */
	PAYER("Pagador"), /**
	 * 
	 */
	PATIENT("Paciente"), /**
	 * 
	 */
	ADMISSION_AGENT("Admisión");

	private String name;

	CredentialTypeEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	/**
	 * @param string
	 * @return return the enum represented by this string or null
	 */
	public static CredentialTypeEnum getByString(String string) {
		if (string == null)
			return null;

		for (CredentialTypeEnum e : CredentialTypeEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (CredentialTypeEnum credentialType : CredentialTypeEnum.values())
			valueMap.put(credentialType.name() + "", credentialType.toString());
		return valueMap;
	}
	
	public static ArrayList<String> getFormItems() {
		ArrayList<String> list = new ArrayList<String>();
		for (CredentialTypeEnum credentialType : CredentialTypeEnum.values())
			list.add(credentialType.toString());
		return list;
	}
}
