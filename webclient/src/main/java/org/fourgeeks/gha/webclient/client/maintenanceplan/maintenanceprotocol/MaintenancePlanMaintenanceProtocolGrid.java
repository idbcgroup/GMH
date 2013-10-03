/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author emiliot
 *
 */
public class MaintenancePlanMaintenanceProtocolGrid extends GhaGrid<MaintenancePlanMaintenanceProtocol> {
	//MaintenancePlanFields
	final GHAGridField freqGridField;
	final GHAGridField periodOfTimeGridField;

	//MaintenanceProtocolFields, common with plan
	final GHAGridField idGridField;
	final GHAGridField nameGridField;
	final GHAGridField descriptionGridField;
	/**
	 * 
	 */
	public MaintenancePlanMaintenanceProtocolGrid() {

		idGridField = new GHAGridField("id", "No");
		nameGridField = new GHAGridField("name", "Nombre");
		descriptionGridField = new GHAGridField("desc", "Descripción");
		freqGridField = new GHAGridField("freq", "Frecuencia");
		periodOfTimeGridField = new GHAGridField("pot", "Periodo de Tiempo");
	}

	public void setMaintenancePlanFields(){
		setEmptyMessage("No existen Planes de Mantenimiento para mostrar.");
		setFields(idGridField, nameGridField, descriptionGridField, freqGridField, periodOfTimeGridField);
	}

	public void setMaintenanceProtocolFields(){
		setEmptyMessage("No existen Protocolos de Mantenimiento para mostrar.");
		setFields(idGridField, nameGridField, descriptionGridField);
	}

}
