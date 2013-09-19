package org.fourgeeks.gha.webclient.client.user.permissions;

import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author alacret
 * 
 */
public class FunctionGrid extends GhaGrid<Function> {

	/**
	 * 
	 */
	public FunctionGrid() {
		super();
		setEmptyMessage("No existen datos que mostrar");

		GHAGridField idGridField = new GHAGridField("time", "Fecha");
		GHAGridField timestampGridField = new GHAGridField("msg", "Mensaje");
		GHAGridField msgGridField = new GHAGridField("ipAdd", "Dirección");

		setFields(idGridField, timestampGridField, msgGridField);
	}

}
