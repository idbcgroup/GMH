package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

//PHYSICIAN, NURSE, PAYER, PATIENT, ADMISSION_AGENT

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
}
