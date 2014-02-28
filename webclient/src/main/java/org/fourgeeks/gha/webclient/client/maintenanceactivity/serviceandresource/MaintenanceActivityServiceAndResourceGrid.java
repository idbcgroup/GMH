package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.smartgwt.client.types.ListGridFieldType;

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
		quantityGridField.setType(ListGridFieldType.INTEGER);
		GHATextItem numerico = new GHATextItem();
		numerico.setKeyPressFilter("[0-9]");
		quantityGridField.setEditorType(numerico);
		/*
		 * setCellFormatter(new CellFormatter() {
		 * 
		 * @Override public String format(Object value, ListGridRecord record,
		 * int rowNum, int colNum) { if (colNum == 2 && ((Integer)
		 * value).equals(0)) return null; return String.valueOf(value); } });
		 */
		quantityGridField.setCanEdit(true);
		setEmptyMessage("No existen recursos para mostrar");

		setFields(codigoGridField, nameGridField, quantityGridField);
	}
}