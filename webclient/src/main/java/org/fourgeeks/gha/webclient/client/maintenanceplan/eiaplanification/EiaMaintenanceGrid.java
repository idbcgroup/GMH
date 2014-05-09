/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.eiaplanification;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author caparicio
 * 
 */
public class EiaMaintenanceGrid extends GhaGrid<EiaTypeMaintenancePlan> {

	final GHAGridField numberGridField, typeGridField, codeGridField,
			nameGridField, quantityGridField, costGridField, statusGridField;

	/**
	 * 
	 */
	public EiaMaintenanceGrid() {

		numberGridField = new GHAGridField("number", "No");
		typeGridField = new GHAGridField("type", "Tipo");
		codeGridField = new GHAGridField("code", "Código");
		nameGridField = new GHAGridField("name", "Nombre");
		quantityGridField = new GHAGridField("quantity", "Cantidad/Duración");
		costGridField = new GHAGridField("cost", "Costo");
		statusGridField = new GHAGridField("status",
				"Estatus de la planificación");

		setEmptyMessage("No existen equipos para mostrar");
		setFields(numberGridField, typeGridField, codeGridField, nameGridField,
				quantityGridField, costGridField, statusGridField);
	}
}