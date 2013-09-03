package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.grids.tree.GHATreeGrid;
import org.fourgeeks.gha.webclient.client.UI.grids.tree.GHATreeGridField;

public class MaintenanceProtocolGrid extends GHATreeGrid<MaintenanceProtocol> {

	public MaintenanceProtocolGrid() {
		setEmptyMessage("No existen protocolos de mantenimiento asociados al plan.");

		GHATreeGridField idGridField = new GHATreeGridField("id", "No.");
		GHATreeGridField nameGridField = new GHATreeGridField("name", "Nombre Prot.");
		GHATreeGridField descriptionGridField = new GHATreeGridField("description", "Descripción del Protocolo");
				
		setFields(idGridField, nameGridField, descriptionGridField);
	}

}
