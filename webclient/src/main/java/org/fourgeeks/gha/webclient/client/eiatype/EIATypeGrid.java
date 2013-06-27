package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.grid.ListGrid;

public class EIATypeGrid extends ListGrid implements ResizeHandler{
	
	public EIATypeGrid() {
		GHAUiHelper.addResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getGridSize(30));
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

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getGridSize(30));
		
	}

}
