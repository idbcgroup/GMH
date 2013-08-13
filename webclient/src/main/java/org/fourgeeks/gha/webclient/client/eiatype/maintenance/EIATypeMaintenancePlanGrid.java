package org.fourgeeks.gha.webclient.client.eiatype.maintenance;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

public class EIATypeMaintenancePlanGrid extends GhaGrid<EiaTypeMaintenancePlan>{

	public EIATypeMaintenancePlanGrid() {
		super();
		setEmptyMessage("No existen Planes de Mantenimiento para mostrar.");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField typeMantGridField = new GHAGridField("typeMant", "Tipo Mantenimiento");
		GHAGridField descriptionGridField = new GHAGridField("description", "Descripción");
		GHAGridField freqGridField = new GHAGridField("freq", "Frecuencia");
		GHAGridField usesGridField = new GHAGridField("uses", "Usos");
		GHAGridField periodOfTimeGridField = new GHAGridField("pod", "Periodo de Tiempo");
		GHAGridField protocolGridField = new GHAGridField("protocol", "Protocolo");
		
		setFields(idGridField, typeMantGridField, descriptionGridField, freqGridField, usesGridField, protocolGridField);
	}

}
