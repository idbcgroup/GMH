package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.google.gwt.event.logical.shared.ResizeHandler;

/**
 * @author alacret, emiliot
 * 
 */
public class EIAGrid extends GhaGrid<Eia> implements ResizeHandler {

	/**
	 * 
	 */
	public EIAGrid() {
		setEmptyMessage("No existen Equipos para mostrar");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField codeGridField = new GHAGridField("code", "Codigo");
		GHAGridField serialGridField = new GHAGridField("serialNumber",
				"Serial");
		GHAGridField nameGridField = new GHAGridField("name", "Nombre");
		GHAGridField locationGridField = new GHAGridField("location",
				"Ubicación");
		GHAGridField statusGridField = new GHAGridField("state", "Estado");

		setFields(idGridField, codeGridField, serialGridField, nameGridField,
				locationGridField, statusGridField);
	}
}
