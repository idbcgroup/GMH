package org.fourgeeks.gha.webclient.client.eia.component;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class EIAComponentGrid extends ListGrid implements ResizeHandler{

	public EIAComponentGrid() {
		GHAUiHelper.addResizeHandler(this);
		
		setWidth100();
		setHeight(GHAUiHelper.getGridSize(30));
		setEmptyMessage("No existen partes de equipos para mostrar.");

		setAlternateRecordStyles(false);
		setCanResizeFields(false);
//		setShowFilterEditor(true);

		ListGridField idGridField = new ListGridField("id", "No");
		idGridField.setAlign(Alignment.CENTER);

		ListGridField partnumGridField = new ListGridField("nParte", "No Parte EIA");
		partnumGridField.setAlign(Alignment.CENTER);
		
		ListGridField nameGridField = new ListGridField("nameParte", "Nombre Parte");
		nameGridField.setAlign(Alignment.CENTER);
		
		ListGridField usoparteGridField = new ListGridField("usoParte", "Uso Parte");
		usoparteGridField.setAlign(Alignment.CENTER);
		
		ListGridField typeGridField = new ListGridField("type", "Tipo Parte");
		typeGridField.setAlign(Alignment.CENTER);

		ListGridField requiredGridField = new ListGridField("req", "Requerido");
		requiredGridField.setAlign(Alignment.CENTER);
		
		ListGridField sustGridField = new ListGridField("sust", "Sustituible");
		sustGridField.setAlign(Alignment.CENTER);

		ListGridField eiaCodeGridField = new ListGridField("eiaCode", "Cod. Equipo");
		eiaCodeGridField.setAlign(Alignment.CENTER);
		
		ListGridField eiaSerialGridField = new ListGridField("eiaSerial", "No. Serial Equipo");
		eiaSerialGridField.setAlign(Alignment.CENTER);
		
		ListGridField facilityGridField = new ListGridField("facility", "Ubicación");
		facilityGridField.setAlign(Alignment.CENTER);
		
		ListGridField statusGridField = new ListGridField("status", "Estado Equipo");
		statusGridField.setAlign(Alignment.CENTER);

		setFields(idGridField, partnumGridField, nameGridField, usoparteGridField, typeGridField, requiredGridField, sustGridField, eiaCodeGridField, eiaSerialGridField, facilityGridField);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getGridSize(30));
	}

}
