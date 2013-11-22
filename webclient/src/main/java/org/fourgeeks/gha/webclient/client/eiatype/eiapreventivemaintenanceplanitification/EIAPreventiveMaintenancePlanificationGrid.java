package org.fourgeeks.gha.webclient.client.eiatype.eiapreventivemaintenanceplanitification;

import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

public class EIAPreventiveMaintenancePlanificationGrid extends
		GhaGrid<EiaDamageReport> {

	public EIAPreventiveMaintenancePlanificationGrid() {
		setEmptyMessage("No existen Equipos con Planificaciones de Mantenimiento Preventivo para mostrar");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField initDateGridField = new GHAGridField("initDate",
				"Fecha de inicio");
		GHAGridField eiaCodeGridField = new GHAGridField("eia", "Equipo");
		GHAGridField planGridField = new GHAGridField("plan",
				"Plan de mantenimiento");

		setFields(idGridField, planGridField, eiaCodeGridField,
				initDateGridField);
	}
}
