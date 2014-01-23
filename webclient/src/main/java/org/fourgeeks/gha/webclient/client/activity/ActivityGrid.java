package org.fourgeeks.gha.webclient.client.activity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * 
 * @author caparicio
 * 
 */
public class ActivityGrid extends GhaGrid<MaintenanceActivity> {

	public ActivityGrid() {
		super();
		setEmptyMessage(GHAStrings.get("no-results-to-show"));

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField nameGridField = new GHAGridField("name", "Nombre");
		GHAGridField descriptionGridField = new GHAGridField("desc",
				"Descripción");
		GHAGridField freqGridField = new GHAGridField("freq", "Frecuencia");
		GHAGridField periodOfTimeGridField = new GHAGridField("pot",
				"Periodo de Tiempo");

		setFields(idGridField, nameGridField, descriptionGridField,
				freqGridField, periodOfTimeGridField);
	}

}
