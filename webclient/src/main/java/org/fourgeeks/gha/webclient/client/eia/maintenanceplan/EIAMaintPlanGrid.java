package org.fourgeeks.gha.webclient.client.eia.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

public class EIAMaintPlanGrid extends GhaGrid<EiaMaintenancePlanification>{

	public EIAMaintPlanGrid() {
		super();
		setEmptyMessage("No existen Planes de mantenimiento para mostrar.");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField eiaTypeNameGridField = new GHAGridField("typeName", "Nombre");
		GHAGridField descriptionGridField = new GHAGridField("description", "Descripción");
		GHAGridField dateGridField = new GHAGridField("date", "Fecha");
		GHAGridField statusGridField = new GHAGridField("status", "Estado");
		
		setFields(idGridField, 
				  eiaTypeNameGridField, 
				  descriptionGridField, 
				  dateGridField, 
				  statusGridField);
	}

}
