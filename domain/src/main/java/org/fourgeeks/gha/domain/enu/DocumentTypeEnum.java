/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author emiliot
 *
 */
public enum DocumentTypeEnum {
	/**
	 * 
	 */
	LOCAL("Cédula"), /**
	 * 
	 */
	PASSPORT("Pasaporte"), /**
	 * 
	 */
	OTHER("Otro");

	private String name;

	DocumentTypeEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	/**
	 * @param string
	 * @return return the enum represented by this string or null
	 */
	public static DocumentTypeEnum getByString(String string) {
		if (string == null)
			return null;

		for (DocumentTypeEnum e : DocumentTypeEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (DocumentTypeEnum documentType : DocumentTypeEnum.values())
			valueMap.put(documentType.name() + "", documentType.toString());
		return valueMap;
	}
}
