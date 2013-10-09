/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author emiliot
 * 
 */
public enum EiaStateEnum {
	/**
	 * 
	 */
	CREATED("Nuevo"), /**
	 * 
	 */
	ACQUIRED("Adquirido"), /**
	 * 
	 */
	RECIBIDO("Recibido"), /**
	 * 
	 */
	DELIVERED("Entregado"), /**
			 * 
			 */
	TO_INSTALL("Por instalar"), /**
			 * 
			 */
	INSTALLED("Instalado"), /**
			 * 
			 */
	LOST("Perdido"), /**
			 * 
			 */
	IN_OPERATION("En operación"), /**
			 * 
			 */
	DAMAGED("Dañado"), /**
			 * 
			 */
	MAINTENANCE("En mantenimiento"), /**
			 * 
			 */
	IN_ACCEPTANCE("En espera por aceptaci'on en mantenimiento"), /**
	 * 
	 */
	DISINCORPORATED("Desincorporado"), /**
			 * 
			 */
	MAYOR_DAMAGED("Dañado severamente");

	private String name;

	EiaStateEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	/**
	 * @param string
	 * @return the EiaStateEnum representated by this value, or null if it does
	 *         not exist
	 */
	public static EiaStateEnum getByString(String string) {
		if (string == null)
			return null;

		for (EiaStateEnum e : EiaStateEnum.values())
			if (string.equals(e.toString()))
				return e;

		return null;
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (EiaStateEnum mobility : EiaStateEnum.values())
			valueMap.put(mobility.name() + "", mobility.toString());
		return valueMap;
	}

	/**
	 * @return a {@link List} of {@link EiaStateEnum}
	 */
	public static List<EiaStateEnum> valuesList() {
		return Arrays.asList(EiaStateEnum.values());
	}
}
