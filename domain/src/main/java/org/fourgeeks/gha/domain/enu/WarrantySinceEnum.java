/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author emiliot
 *
 */
public enum WarrantySinceEnum {
	PURCHASE("Adquisición"),
	RECEPTION("Recepción"),
	INSTALATION("Instalación"),
	ACCEPTATION("Aceptación");
	
	private String name;
	
	WarrantySinceEnum(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
	/**
	 * @param string
	 * @return the TimePeriodEnum representated by this value, or null if it does
	 *         not exist
	 */
	public static WarrantySinceEnum getByString(String string) {
		if (string == null)
			return null;

		for (WarrantySinceEnum e : WarrantySinceEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}
	
	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (WarrantySinceEnum garantiaDesde : WarrantySinceEnum.values())
			valueMap.put(garantiaDesde.name() + "", garantiaDesde.toString());
		return valueMap;
	}
}
