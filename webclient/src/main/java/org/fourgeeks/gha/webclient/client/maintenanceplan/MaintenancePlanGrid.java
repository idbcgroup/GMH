package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

public class MaintenancePlanGrid extends GhaGrid<MaintenancePlan>{

	public MaintenancePlanGrid() {
		super();
		setEmptyMessage("No existen Planes de Mantenimiento para mostrar.");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField nameGridField = new GHAGridField("name", "Nombre");
		GHAGridField descriptionGridField = new GHAGridField("desc", "Descripción");
		GHAGridField freqGridField = new GHAGridField("freq", "Frecuencia");
		GHAGridField periodOfTimeGridField = new GHAGridField("pot", "Periodo de Tiempo");
		
		setFields(idGridField, nameGridField, descriptionGridField, freqGridField, periodOfTimeGridField);
	}

}
