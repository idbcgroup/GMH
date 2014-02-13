package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceActivityServiceAndResourceGrid extends
		GhaGrid<RequiredResources> {
	/**
	 * 
	 */
	public MaintenanceActivityServiceAndResourceGrid() {

		final GHAGridField codigoGridField = new GHAGridField("code", "Código");
		final GHAGridField nameGridField = new GHAGridField("name", "Nombre");
		final GHAGridField quantityGridField = new GHAGridField("quantity",
				"Cantidad");

		setCellFormatter(new CellFormatter() {

			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				if (colNum == 2 && ((Integer) value).equals(0))
					return null;
				return String.valueOf(value);
			}
		});
		quantityGridField.setCanEdit(true);
		setEmptyMessage("No existen recursos para mostrar");

		setFields(codigoGridField, nameGridField, quantityGridField);
	}
}