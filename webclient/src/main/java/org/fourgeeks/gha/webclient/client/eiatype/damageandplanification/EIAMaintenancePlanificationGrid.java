package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import java.util.Date;

import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author naramirez
 */
public class EIAMaintenancePlanificationGrid extends GhaGrid<EiaDamageReport> {

	/**
	 * 
	 */
	public EIAMaintenancePlanificationGrid() {
		setEmptyMessage("No existen Equipos con Planificaciones de Mantenimiento Preventivo para mostrar");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField initDateGridField = new GHAGridField("initDate",
				"Fecha de inicio");
		GHAGridField eiaCodeGridField = new GHAGridField("eia", "Equipo");
		GHAGridField planGridField = new GHAGridField("plan",
				"Plan de mantenimiento");

		setFields(idGridField, planGridField, eiaCodeGridField,
				initDateGridField);

		initDateGridField.setCellFormatter(new CellFormatter() {
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				if (value != null) {
					Date dateValue = (Date) value;
					return DateTimeFormat.getFormat("dd/MM/yyyy").format(
							dateValue);
				}
				return "";
			}
		});
	}
}
