/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

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
	IN_ACCEPTANCE("En esperapor aceptaci'on en mantenimiento"), /**
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
}
