package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.grids.tree.GHATreeGrid;
import org.fourgeeks.gha.webclient.client.UI.grids.tree.GHATreeGridField;

public class MaintenanceActivityGrid extends GHATreeGrid<MaintenanceActivity> {

	public MaintenanceActivityGrid() {
		setEmptyMessage("No existen Actividades para mostrar.");

		GHATreeGridField idGridField = new GHATreeGridField("id", "No.");
		GHATreeGridField nameGridField = new GHATreeGridField("name", "Nombre Actividad");
		GHATreeGridField descriptionGridField = new GHATreeGridField("description", "Descripción");
				
		setFields(idGridField, nameGridField, descriptionGridField);
	}

}
