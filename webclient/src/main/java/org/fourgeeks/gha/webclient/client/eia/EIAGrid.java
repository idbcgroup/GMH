package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.grid.ListGrid;

public class EIAGrid extends ListGrid {

	public EIAGrid() {
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setEmptyMessage("No existen tipos de equipo para mostrar");
		setAlternateRecordStyles(false);
		setCanResizeFields(false);

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
