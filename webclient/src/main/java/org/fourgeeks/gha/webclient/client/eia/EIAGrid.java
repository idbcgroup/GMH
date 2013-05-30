package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.util.ListGridFieldDate;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class EIAGrid extends ListGrid {

	public EIAGrid() {
		setWidth100();
		setHeight100();
		setMargin(20);
		setStylePrimaryName("gha-grid");
		// setHeaderBarStyle("gha-grid-header-bar");
		// setHeaderBaseStyle("gha-grid-header-base");
		// setStyleName("top-margin");
		// setStyleName("sides-padding");
		// setShowAllRecords(true);
		setAlternateRecordStyles(false);
		setCanResizeFields(false);

		ListGridField numSolicCambioNuevas = new ListGridField("numSolicitud",
				"N°");
		numSolicCambioNuevas.setAlign(Alignment.CENTER);
		numSolicCambioNuevas.setPrompt("Solicitud");
		numSolicCambioNuevas.setHeaderBaseStyle("gha-grid-header-base2");
		numSolicCambioNuevas.setBaseStyle("gha-grid-header-base");
		numSolicCambioNuevas.setHeaderTitleStyle("gha-grid-header-title");

		ListGridField tituloCambioNuevas = new ListGridField("titulo", "Titulo");
		tituloCambioNuevas.setAlign(Alignment.CENTER);
		tituloCambioNuevas.setPrompt("Titulo de la solicitud de cambio");

		ListGridField prioridadCambioNuevas = new ListGridField("prioridad",
				"Prioridad");
		prioridadCambioNuevas.setAlign(Alignment.CENTER);
		prioridadCambioNuevas.setPrompt("Prioridad de la solicitud de cambio");
		prioridadCambioNuevas.setCellFormatter(new CellFormatter() {
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				if (value != null) {
					try {
						int priorida = (Integer) value;
						switch (priorida) {
						case 1:
							return "Normal";
						case 2:
							return "Media";
						case 3:
							return "Urgente";
						default:
							return "";
						}
					} catch (Exception e) {
						return value.toString() + ": " + e;
					}
				}
				return "";
			}
		});

		ListGridField estadoCambioNuevas = new ListGridField("idEstadoSDC",
				"Estado");
		estadoCambioNuevas.setAlign(Alignment.CENTER);
		estadoCambioNuevas.setPrompt("Estado de la solicitud de cambio");
		estadoCambioNuevas.setCellFormatter(new CellFormatter() {
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				if (value != null) {
					try {
						int estado = (Integer) value;
						switch (estado) {
						case 1:
							return "Nuevo";
						case 2:
							return "Aceptado";
						case 3:
							return "Rechazado";
						case 4:
							return "Aprobado";
						case 5:
							return "No aprobado";
						case 6:
							return "En construcción";
						case 7:
							return "En prueba";
						case 8:
							return "En implementación";
						case 9:
							return "Finalizado";
						case 11:
							return "Aprobado Jcc";
						case 12:
							return "No aprobado Jcc";
						case 13:
							return "Atendido";
						default:
							return "";
						}
					} catch (Exception e) {
						return value.toString() + ": " + e;
					}
				}
				return "";
			}
		});

		ListGridField anaAsignadoCambioNuevas = new ListGridField(
				"idAnalistaActual", "Asignado a", 93);
		anaAsignadoCambioNuevas.setAlign(Alignment.CENTER);
		anaAsignadoCambioNuevas.setPrompt("Analista asignado");
		anaAsignadoCambioNuevas.setEmptyCellValue("No asignado");

		ListGridFieldDate fechaReportado = new ListGridFieldDate("fechaSDC",
				"Reportado", 89);
		fechaReportado.setAlign(Alignment.CENTER);
		fechaReportado.setPrompt("Fecha de reporte de la solicitud de cambio");

		setFields(numSolicCambioNuevas, tituloCambioNuevas,
				prioridadCambioNuevas, estadoCambioNuevas,
				anaAsignadoCambioNuevas, fechaReportado);

	}
}
