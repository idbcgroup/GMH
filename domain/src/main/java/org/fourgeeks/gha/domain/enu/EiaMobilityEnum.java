/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

/**
 * @author emiliot
 * 
 */
public enum EiaMobilityEnum {
	FIXED("Fijado"), MOVABLE("Movible"), PORTABLE("Portable");

	private String name;

	EiaMobilityEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
