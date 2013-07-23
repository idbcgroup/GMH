package org.fourgeeks.gha.webclient.client.edt;

import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.grid.ListGrid;

public class EDTTopGrid extends ListGrid implements ResizeHandler{

	public EDTTopGrid() {
		GHAUiHelper.addResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getEDTGridSize(115));
		setEmptyMessage("No existen actividades para mostrar");
		setAlternateRecordStyles(false);
		setCanResizeFields(false);

		GHAGridField dateGridField = new GHAGridField("date", "fecha");
		GHAGridField hourGridField = new GHAGridField("hour", "Hora");
		GHAGridField ciGridField = new GHAGridField("ci","Cédula");
		GHAGridField nameGridField = new GHAGridField("pacientName", "Paciente");
		GHAGridField activityGridField = new GHAGridField("act", "Actividad");
		GHAGridField statusGridField = new GHAGridField("status", "Estado");
		GHAGridField priorityGridField = new GHAGridField("priority", "Prioridad");
		
		setFields(dateGridField, hourGridField, ciGridField, nameGridField,
				activityGridField, statusGridField, priorityGridField);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getEDTGridSize(115));
	}
}
