package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

public class MaintenancePlanMaintenanceProtocolGrid extends GhaGrid<MaintenanceProtocol> {

	public MaintenancePlanMaintenanceProtocolGrid() {
		setEmptyMessage("No existen protocolos de mantenimiento.");

		GHAGridField idGridField = new GHAGridField("id", "No.");
		GHAGridField nameGridField = new GHAGridField("name", "Nombre Protocolo");
		GHAGridField descriptionGridField = new GHAGridField("description", "Descripción del Protocolo");
				
		setFields(idGridField, nameGridField, descriptionGridField);
	}

}
