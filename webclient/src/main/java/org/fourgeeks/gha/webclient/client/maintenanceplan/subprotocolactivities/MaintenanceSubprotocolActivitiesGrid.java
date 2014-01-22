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

	/**
	 * 
	 */
	public MaintenanceSubprotocolActivitiesGrid() {

		final GHAGridField ordinalGridField = new GHAGridField("ordinal",
				"Secuencia");
		final GHAGridField typeGridField = new GHAGridField("type", "Tipo");
		final GHAGridField codeGridField = new GHAGridField("code", "Código");
		final GHAGridField nameGridField = new GHAGridField("name", "Nombre");

		final GHAGridField timeGridField = new GHAGridField("time", "Duración");
		final GHAGridField periodOfTimeGridField = new GHAGridField("pot",
				"Periodo de Tiempo");
		final GHAGridField costGridField = new GHAGridField("cost", "Costo");
		final GHAGridField currencyGridField = new GHAGridField("currency",
				"Moneda");

		setEmptyMessage("No existen actividades para mostrar");

		setFields(ordinalGridField, typeGridField, codeGridField,
				nameGridField, timeGridField, periodOfTimeGridField,
				costGridField, currencyGridField);
	}
}