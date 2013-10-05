package org.fourgeeks.gha.webclient.client.user.loginlog;

import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author alacret
 * 
 */
public class LogonLogGrid extends GhaGrid<LogonLog> {

	/**
	 * 
	 */
	public LogonLogGrid() {
		super();
		setEmptyMessage("No existen Mensajes que mostrar.");

		GHAGridField idGridField = new GHAGridField("time", "Fecha");
		GHAGridField timestampGridField = new GHAGridField("msg", "Mensaje");
		GHAGridField msgGridField = new GHAGridField("ipAdd", "Dirección");

		setFields(idGridField, timestampGridField, msgGridField);
	}

}
