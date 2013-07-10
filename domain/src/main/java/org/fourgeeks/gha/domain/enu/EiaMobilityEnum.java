/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

public enum EiaMobilityEnum {
	FIXED("Fijado"), MOVABLE("Movible"), PORTABLE("Portable");

	private String name;

	EiaMobilityEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public static EiaMobilityEnum getByString(String string) {
		if (string == null)
			return null;

		for (EiaMobilityEnum e : EiaMobilityEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}
}
