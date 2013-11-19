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
	CREATED("Nuevo", "eiastate-created"), /**
	 * 
	 */
	ACQUIRED("Adquirido", "eiastate-acquired"), /**
	 * 
	 */
	RECIBIDO("Recibido", "eiastate-received"), /**
	 * 
	 */
	DELIVERED("Entregado", "eiastate-delivered"), /**
			 * 
			 */
	TO_INSTALL("Por instalar", "eiastate-toinstall"), /**
			 * 
			 */
	INSTALLED("Instalado", "eiastate-installed"), /**
			 * 
			 */
	LOST("Perdido", "eiastate-lost"), /**
			 * 
			 */
	IN_OPERATION("En operación", "eiastate-inoperation"), /**
			 * 
			 */
	DAMAGED("Dañado", "eiastate-damaged"), /**
			 * 
			 */
	MAINTENANCE("En mantenimiento", "eiastate-maintenance"), /**
			 * 
			 */
	IN_ACCEPTANCE("En espera por aceptaci'on en mantenimiento",
			"eiastate-inacceptance"), /**
	 * 
	 */
	DISINCORPORATED("Desincorporado", "eiastate-disincorporated"), /**
			 * 
			 */
	MAYOR_DAMAGED("Dañado severamente", "eiastate-majordamaged");

	private String name;
	private String uiKey;

	EiaStateEnum(String name, String uiKey) {
		this.name = name;
		this.uiKey = uiKey;
	}

	@Override
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

	public static String getUiKey(EiaStateEnum state) {
		return state.uiKey;
	}
}
