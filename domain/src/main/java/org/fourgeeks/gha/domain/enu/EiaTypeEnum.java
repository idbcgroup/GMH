/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

/**
 * @author emiliot
 * 
 */
public enum EiaTypeEnum {
	EQUIPMENT("Equipo"), INSTALLATION("Instalación"), PART("Parte"), REPLACEMENT(
			"Repuesto");

	private String name;

	EiaTypeEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public static EiaTypeEnum getByString(String string) {
		if (string == null)
			return null;

		for (EiaTypeEnum e : EiaTypeEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}
}
