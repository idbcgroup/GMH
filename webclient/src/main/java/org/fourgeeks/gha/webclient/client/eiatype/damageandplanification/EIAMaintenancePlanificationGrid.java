package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

public class EIAMaintenancePlanificationGrid extends
		GhaGrid<EiaMaintenancePlanification> {

	public EIAMaintenancePlanificationGrid() {
		setEmptyMessage("No existen Equipos con Planificaciones de Mantenimiento Preventivo para mostrar");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField initDateGridField = new GHAGridField("initDate",
				"Fecha de inicio");
		GHAGridField eiaCodeGridField = new GHAGridField("eia", "Equipo");
		GHAGridField typeGridField = new GHAGridField("type",
				"Tipo de mantenimiento");
		GHAGridField statusGridField = new GHAGridField("status", "Estado");

		setFields(idGridField, typeGridField, statusGridField,
				eiaCodeGridField, initDateGridField);
	}
}
