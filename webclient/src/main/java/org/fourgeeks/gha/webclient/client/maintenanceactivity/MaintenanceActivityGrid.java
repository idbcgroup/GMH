package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author naramirez
 * 
 */
public class MaintenanceActivityGrid extends GhaGrid<MaintenanceActivity> {

	/**
	 * 
	 */
	public MaintenanceActivityGrid() {
		setEmptyMessage("No existen Actividades para mostrar.");

		final GHAGridField idGridField = new GHAGridField("id", "No");
		final GHAGridField typeGridField = new GHAGridField("type",
				GHAStrings.get("type"));
		final GHAGridField codeGridField = new GHAGridField("code",
				GHAStrings.get("code"));
		final GHAGridField nameGridField = new GHAGridField("name",
				GHAStrings.get("name"));
		final GHAGridField timeGridField = new GHAGridField("time", "Duración");
		final GHAGridField potGridField = new GHAGridField("pot",
				GHAStrings.get("period-of-time"));
		final GHAGridField costGridField = new GHAGridField("cost",
				GHAStrings.get("cost"));
		final GHAGridField currencyGridField = new GHAGridField("currency",
				GHAStrings.get("currency"));

		setFields(idGridField, typeGridField, codeGridField, nameGridField,
				timeGridField, potGridField, costGridField, currencyGridField);
	}

}
