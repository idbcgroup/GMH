package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import java.util.Date;

import org.fourgeeks.gha.domain.gmh.EiaMaintenance;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author naramirez
 */
public class EIAMaintenanceGrid extends GhaGrid<EiaMaintenance> {

	/**
	 * 
	 */
	public EIAMaintenanceGrid() {
		setEmptyMessage("No existen Equipos con Mantenimientos para mostrar");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField eiaCodeGridField = new GHAGridField("eia", "Equipo");
		GHAGridField typeGridField = new GHAGridField("type", "Tipo de Mant.");
		GHAGridField statusGridField = new GHAGridField("status", "Estado");
		GHAGridField initDateGridField = new GHAGridField("initDate",
				"Fecha inicio");

		setFields(idGridField, typeGridField, statusGridField,
				eiaCodeGridField, initDateGridField);

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
