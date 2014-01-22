/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.subprotocolactivities;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author caparicio
 * 
 */
public class MaintenanceSubprotocolActivitiesGrid extends
		GhaGrid<EiaTypeMaintenancePlan> {

	final GHAGridField numberGridField;
	final GHAGridField typeGridField;
	final GHAGridField codeGridField;
	final GHAGridField nameGridField;
	final GHAGridField durationGridField;
	final GHAGridField costGridField;

	/**
	 * 
	 */
	public MaintenanceSubprotocolActivitiesGrid() {

		numberGridField = new GHAGridField("number", "No");
		typeGridField = new GHAGridField("type", "Tipo");
		codeGridField = new GHAGridField("code", "Código");
		nameGridField = new GHAGridField("name", "Nombre");
		durationGridField = new GHAGridField("duration", "Duración");
		costGridField = new GHAGridField("cost", "Costo");

		setEmptyMessage("No existen equipos para mostrar");
		setFields(numberGridField, typeGridField, codeGridField, nameGridField,
				durationGridField, costGridField);
	}
}