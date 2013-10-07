package org.fourgeeks.gha.webclient.client.maintenanceprotocol.activities;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivityMaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author emiliot
 * 
 */
public class MaintenanceActivityMaintenanceProtocolGrid extends
		GhaGrid<MaintenanceActivityMaintenanceProtocol> {
	// MaintenanceProtocolFields, common with plan
	final GHAGridField idGridField;
	final GHAGridField nameGridField;
	final GHAGridField descriptionGridField;

	public MaintenanceActivityMaintenanceProtocolGrid() {
		idGridField = new GHAGridField("id", "No");
		nameGridField = new GHAGridField("name", "Nombre");
		descriptionGridField = new GHAGridField("desc", "Descripción");

		setEmptyMessage("No existen actividades de mantenimiento para mostrar.");
		setFields(idGridField, nameGridField, descriptionGridField);
	}

}
