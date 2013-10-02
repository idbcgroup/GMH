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

	/**
	 * 
	 */
	public EiaTypeMaintenancePlanGrid() {
		setEmptyMessage("No existen tipos de equipo para mostrar");
		
		GHAGridField codeGridField = new GHAGridField("code", "Codigo");
		GHAGridField nameGridField = new GHAGridField("name", "Nombre");
		GHAGridField brandGridField = new GHAGridField("brand", "Marca");
		GHAGridField modelGridField = new GHAGridField("model", "Modelo");
		GHAGridField makeGridField = new GHAGridField("manufacturer",
				"Fabricante");

		setFields(codeGridField, nameGridField, brandGridField,
				modelGridField, makeGridField);
	}
	
}
