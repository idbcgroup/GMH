package org.fourgeeks.gha.webclient.client.eia.partes;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class EIAPartesEIAtypeGrid extends ListGrid {

	public EIAPartesEIAtypeGrid() {
		setWidth100();
		setHeight("300px");
		setEmptyMessage("No existen tipos de equipo para mostrar");

		setAlternateRecordStyles(false);
		setCanResizeFields(false);
		setShowFilterEditor(true);

		ListGridField idGridField = new ListGridField("id", "No");
		idGridField.setAlign(Alignment.CENTER);

		ListGridField codeGridField = new ListGridField("code", "Código");
		codeGridField.setAlign(Alignment.CENTER);
		
		ListGridField serialGridField = new ListGridField("serial", "Serial");
		serialGridField.setAlign(Alignment.CENTER);
		
		ListGridField nameGridField = new ListGridField("name", "Nombre");
		nameGridField.setAlign(Alignment.CENTER);
		
		ListGridField facilityGridField = new ListGridField("facility", "Ubicación");
		facilityGridField.setAlign(Alignment.CENTER);

		ListGridField statusGridField = new ListGridField("status", "Status");
		statusGridField.setAlign(Alignment.CENTER);

		setFields(idGridField, codeGridField, serialGridField, facilityGridField, statusGridField);
	}

}
