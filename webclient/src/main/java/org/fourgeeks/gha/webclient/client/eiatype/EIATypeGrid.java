package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GhaGrid;

public class EIATypeGrid extends GhaGrid<EiaType> {

	public EIATypeGrid() {
		
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