package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

import com.google.gwt.event.logical.shared.ResizeHandler;

public class EIAGrid extends GhaGrid<Eia> implements ResizeHandler {

	public EIAGrid() {
		setEmptyMessage("No existen Equipos para mostrar");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField codeGridField = new GHAGridField("code", "Codigo");
		GHAGridField serialGridField = new GHAGridField("serialNumber",
				"Serial");
		GHAGridField nameGridField = new GHAGridField("eiaTypeName", "Nombre");
		GHAGridField brandGridField = new GHAGridField("brandName", "Marca");
		GHAGridField modelGridField = new GHAGridField("eiaTypeModel", "Modelo");
		GHAGridField makeGridField = new GHAGridField("manufacturerName",
				"Fabricante");

		setFields(idGridField, codeGridField, serialGridField, nameGridField,
				brandGridField, modelGridField, makeGridField);
	}
}
