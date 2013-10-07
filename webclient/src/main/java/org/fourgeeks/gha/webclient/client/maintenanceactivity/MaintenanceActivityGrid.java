package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;
import org.fourgeeks.gha.webclient.client.UI.grids.tree.GHATreeGridField;

public class MaintenanceActivityGrid extends GhaGrid<MaintenanceActivity> {

	public MaintenanceActivityGrid() {
		setEmptyMessage("No existen Actividades para mostrar.");

		GHATreeGridField idGridField = new GHATreeGridField("id", "No.");
		GHATreeGridField nameGridField = new GHATreeGridField("name",
				"Nombre Actividad");
		GHATreeGridField descriptionGridField = new GHATreeGridField("desc",
				"Descripción");

		setFields(idGridField, nameGridField, descriptionGridField);
	}

}
