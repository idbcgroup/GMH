package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

public class EIAMaintenanceGrid extends
		GhaGrid<EiaMaintenancePlanification> {

	public EIAMaintenanceGrid() {
		setEmptyMessage("No existen Equipos con Planificaciones de Mantenimiento Preventivo para mostrar");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField initDateGridField = new GHAGridField("initDate",
				"Fecha inicio");
		GHAGridField eiaCodeGridField = new GHAGridField("eia", "Equipo");
		GHAGridField typeGridField = new GHAGridField("type", "Tipo de Mant.");
		GHAGridField statusGridField = new GHAGridField("status", "Estado");

		setFields(idGridField, typeGridField, statusGridField,
				eiaCodeGridField, initDateGridField);
	}
}