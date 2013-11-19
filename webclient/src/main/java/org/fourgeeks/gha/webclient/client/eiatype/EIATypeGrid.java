package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

public class EIATypeGrid extends GhaGrid<EiaType> {

	public EIATypeGrid() {
		super();
		setEmptyMessage(GHAStrings.get("no-results-to-show"));

		GHAGridField nameGridField = new GHAGridField("name", "Nombre");
		GHAGridField brandGridField = new GHAGridField("brand", "Marca");
		GHAGridField modelGridField = new GHAGridField("model", "Modelo");
		GHAGridField typeGridField = new GHAGridField("type", "Tipo");
		GHAGridField subTypeGridField = new GHAGridField("subtype", " Sub tipo");

		setFields(typeGridField, subTypeGridField, nameGridField,
				brandGridField, modelGridField);
	}

}