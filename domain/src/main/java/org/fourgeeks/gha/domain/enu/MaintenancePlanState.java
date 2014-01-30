/**
 * 
 */
package org.fourgeeks.gha.domain.enu;

/**
 * @author alacret
 */
public enum MaintenancePlanState {
	/**
	 * Creado: El plan a sido creado y no a sido asignado a ningun equipo o tipo
	 * de equipo
	 */
	CREATED,
	/**
	 * Asignado: El plan se ha asociado a algún Tipo de Equipo.
	 */
	ASIGNED,
	/**
	 * Activo: El plan se ha asignado a algun equipo.
	 */
	ACTIVE,
	/**
	 * Inactivo: El plan se inactiva para que no se pueda asignar a más equipos;
	 * no debe tener asignaciones en EiaType o estar activo en algún equipo para
	 * poder desactivarlo.
	 */
	INACTIVE,
	/**
	 * EliminadoEliminado: El plan se marca eliminado (Eliminación Lógica) sólo
	 * se podrá borrar si no hay historia de ejecución del plan en equipos.
	 */
	DELETED;
}
