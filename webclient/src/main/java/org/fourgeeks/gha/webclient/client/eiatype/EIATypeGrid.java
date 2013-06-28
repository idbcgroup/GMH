package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.grid.ListGrid;

public class EIATypeGrid extends ListGrid {

	public EIATypeGrid() {
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setEmptyMessage("No existen tipos de equipo para mostrar");
		setAlternateRecordStyles(false);
		setCanResizeFields(false);

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField codeGridField = new GHAGridField("code", "Codigo");
		GHAGridField nameGridField = new GHAGridField("name", "Nombre");
		GHAGridField brandGridField = new GHAGridField("brand", "Marca");
		GHAGridField modelGridField = new GHAGridField("model", "Modelo");
		GHAGridField makeGridField = new GHAGridField("manufacturer",
				"Fabricante");

		setFields(idGridField, codeGridField, nameGridField, brandGridField,
				modelGridField, makeGridField);
	}
}