/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

/**
 * @author emiliot
 * 
 */
public enum EiaSubTypeEnum {
	LIFE_SUPPORT("Suporte de vida"), DIAGNOSE("Diágnostico"), MEDICATION(
			"Medicación"), IT_SYSTEM("Sistema IT");

	private String name;

	EiaSubTypeEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}