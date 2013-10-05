/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author emiliot
 *
 */
public class EiaTypeMaintenancePlanGrid extends GhaGrid<EiaTypeMaintenancePlan> {
	final GHAGridField codeGridField;
	final GHAGridField nameGridField;
	final GHAGridField brandGridField;
	final GHAGridField modelGridField;
	final GHAGridField manufacturerGridField;
	
	final GHAGridField descriptionGridField;
	final GHAGridField freqGridField;
	final GHAGridField periodOfTimeGridField;
	
	
	/**
	 * 
	 */
	public EiaTypeMaintenancePlanGrid() {
		
		
		codeGridField = new GHAGridField("code", "Codigo");
		nameGridField = new GHAGridField("name", "Nombre");
		brandGridField = new GHAGridField("brand", "Marca");
		modelGridField = new GHAGridField("model", "Modelo");
		manufacturerGridField = new GHAGridField("manufacturer",
				"Fabricante");
		
		descriptionGridField = new GHAGridField("desc", "Descripción");
		freqGridField = new GHAGridField("freq", "Frecuencia");
		periodOfTimeGridField = new GHAGridField("pot", "Periodo de Tiempo");
	}
	
	public void setEiaTypeFields(){
		setEmptyMessage("No existen tipos de equipo para mostrar");
		setFields(codeGridField, nameGridField, brandGridField,
				modelGridField, manufacturerGridField);
	}
	
	public void setMaintenancePlanFields(){
		setEmptyMessage("No existen Planes de Mantenimiento para mostrar.");
		setFields(nameGridField, descriptionGridField, freqGridField, periodOfTimeGridField);
	}
}
