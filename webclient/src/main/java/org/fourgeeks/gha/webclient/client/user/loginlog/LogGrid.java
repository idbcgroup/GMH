package org.fourgeeks.gha.webclient.client.user.loginlog;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

public class LogGrid extends GhaGrid<MaintenancePlan>{

	public LogGrid() {
		super();
		setEmptyMessage("No existen Mensajes que mostrar.");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField timestampGridField = new GHAGridField("timestamp", "Fecha");
		GHAGridField msgGridField = new GHAGridField("msg", "Mensaje");
		
		setFields(idGridField, timestampGridField, msgGridField);
	}

}
