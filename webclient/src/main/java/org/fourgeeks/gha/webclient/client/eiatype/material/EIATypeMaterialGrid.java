package org.fourgeeks.gha.webclient.client.eiatype.material;

import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.grid.ListGrid;

public class EIATypeMaterialGrid extends ListGrid implements ResizeHandler {

	private static final int GRID_SIZE = GHAUiHelper.getGridSize(30);

	public EIATypeMaterialGrid() {
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setHeight(GRID_SIZE);
		setEmptyMessage("No existen materiales para mostrar.");

		setAlternateRecordStyles(false);
		setCanResizeFields(false);

		setFields(new GHAGridField("id", "No"),
				new GHAGridField("code", "Codigo"),
				new GHAGridField("name", "Nombre"),
				new GHAGridField("type", "Tipo"),
				new GHAGridField("model", "Modelo"));
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GRID_SIZE);
	}

}
