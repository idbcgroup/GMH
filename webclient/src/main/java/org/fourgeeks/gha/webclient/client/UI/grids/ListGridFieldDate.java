package org.fourgeeks.gha.webclient.client.UI.grids;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 
 * @author alacret
 * 
 *         Campo de los grid que maneja el mostrar la fecha
 * 
 */
public class ListGridFieldDate extends ListGridField {

	public ListGridFieldDate(String name, int width) {
		super(name, width);
		setFormateador();
	}

	public ListGridFieldDate(String name, String title, int width) {
		super(name, title, width);
		setFormateador();
	}

	public ListGridFieldDate(String name, String title) {
		super(name, title);
		setFormateador();
	}

	public ListGridFieldDate(String name) {
		super(name);
		setFormateador();
	}

	private void setFormateador() {
		setType(ListGridFieldType.DATE);
		setCellFormatter(new CellFormatter() {

			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				if (value != null)
					try {
						Date dateValue = (Date) value;
						DateTimeFormat dateFormatterEvento = DateTimeFormat
								.getFormat("dd/MMM/yyyy");
						return dateFormatterEvento.format(dateValue);
					} catch (Exception e) {
						return value.toString() + ": " + e;
					}
				else
					return "";

			}
		});

	}
}
