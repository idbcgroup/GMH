/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

/**
 * @author emiliot
 * 
 */
public enum EiaStateEnum {
	NUEVO("Nuevo"), USADO("Usado"), DAÑADO("Dañado");

	private String name;

	EiaStateEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public static EiaStateEnum getByString(String string) {
		if (string == null)
			return null;

		for (EiaStateEnum e : EiaStateEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}
}
