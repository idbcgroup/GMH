package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import org.fourgeeks.gha.domain.gmh.ServiceAndResource;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceActivityServiceAndResourceGrid extends
		GhaGrid<ServiceAndResource> {
	/**
	 * 
	 */
	public MaintenanceActivityServiceAndResourceGrid() {

		final GHAGridField codigoGridField = new GHAGridField("code", "Código");
		final GHAGridField nameGridField = new GHAGridField("name", "Nombre");
		final GHAGridField quantityGridField = new GHAGridField("quantity",
				"Cantidad");

		setEmptyMessage("No existen recursos para mostrar");

		setFields(codigoGridField, nameGridField, quantityGridField);
	}
}