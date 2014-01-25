/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

import java.util.LinkedHashMap;

/**
 * @author naramirez
 */
public enum ActivityState {
	/**
	 * Creado: La actividad a sido creada y no a sido asignada a ningun
	 * protocolo o subprotocolo
	 */
	CREATED,
	/**
	 * Activo: La actividad se ha asignado a protocolos y/o subprotocolos.
	 */
	ACTIVE,
	/**
	 * Inactivo: La actividad se inactiva para que no se pueda asignar a más
	 * protocolos y subprotocolos. La actividad no debe tener asignaciones en
	 * protocolos o subprotocolos para poder desactivarla.
	 */
	INACTIVE,
	/**
	 * Eliminado: La actividad se marca como eliminada (Eliminación Lógica).
	 * Solo se podrá borrar de la base de datos si no ha sido utilizada y existe
	 * historia de planes con esta actividad.
	 */
	DELETED;

	/**
	 * @return a valueMap with this Enum values
	 */
	public static LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (ActivityState value : ActivityState.values())
			valueMap.put(value.name() + "", value.toString());
		return valueMap;
	}
}
