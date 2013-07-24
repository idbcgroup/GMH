package org.fourgeeks.gha.webclient.client.eiatype.component;

import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.grid.ListGrid;

public class EIATypeComponentGrid extends ListGrid implements ResizeHandler {

	private static final int GRID_SIZE = GHAUiHelper.getGridSize(30); 
	
	public EIATypeComponentGrid() {
		GHAUiHelper.addResizeHandler(this);
		setWidth100();
		setHeight(GRID_SIZE);
		setEmptyMessage("No existen componentes para mostrar");
		setAlternateRecordStyles(false);
		setCanResizeFields(false);
		setFields(new GHAGridField("id", "No"),
				new GHAGridField("code", "Codigo"),
				new GHAGridField("name", "Nombre"),
				new GHAGridField("brand", "Marca"),
				new GHAGridField("model", "Modelo"),
				new GHAGridField("required", "Requerido"),
				new GHAGridField("replaceable", "Reemplazable")
				);
	}
	
	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GRID_SIZE);
	}

}
