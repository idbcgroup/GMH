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
}
