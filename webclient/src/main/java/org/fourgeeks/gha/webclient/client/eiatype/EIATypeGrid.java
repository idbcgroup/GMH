package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class EIATypeGrid extends ListGrid {

	public EIATypeGrid() {
		setWidth100();
		setHeight100();
		setEmptyMessage("No existen tipos de equipo para mostrar");
		// setAlternateRecordStyles("");
		// setAlternateBodyStyleName("");
		// setBodyStyleName("");
		// setEmptyMessageStyle("");
		// setHeaderBarStyle("gha-grid-header-bar");
		// setHeaderBaseStyle("gha-grid-header-base");
		// setHeaderTitleStyle("");
		// setHoverStyle("");
		// setStyleName("top-margin");
		// setFrozenBaseStyle("")
		// setFrozenHeaderBaseStyle("")
		// setFrozenHeaderTitleStyle("");
		// setLoadingDataMessageStyle("");
		// setNormalBaseStyle("");
		// setOfflineMessageStyle("");
		// setOriginBaseStyle("");
		// setRecordBaseStyleProperty("");
		// setSortNumeralStyle("");

		// setShowAllRecords(true);
		setAlternateRecordStyles(false);
		setCanResizeFields(false);

		ListGridField idGridField = new ListGridField("id", "No");
		idGridField.setAlign(Alignment.CENTER);
		// numSolicCambioNuevas.setPrompt("Solicitud");

		ListGridField codeGridField = new ListGridField("code", "Codigo");
		codeGridField.setAlign(Alignment.CENTER);
		// tituloCambioNuevas.setPrompt("Titulo de la solicitud de cambio");

		ListGridField nameGridField = new ListGridField("name", "Nombre");
		nameGridField.setAlign(Alignment.CENTER);
		// prioridadCambioNuevas.setPrompt("Prioridad de la solicitud de cambio");
		// prioridadCambioNuevas.setCellFormatter(new CellFormatter() {
		// @Override
		// public String format(Object value, ListGridRecord record,
		// int rowNum, int colNum) {
		// if (value != null) {
		// try {
		// int priorida = (Integer) value;
		// switch (priorida) {
		// case 1:
		// return "Normal";
		// case 2:
		// return "Media";
		// case 3:
		// return "Urgente";
		// default:
		// return "";
		// }
		// } catch (Exception e) {
		// return value.toString() + ": " + e;
		// }
		// }
		// return "";
		// }
		// });

		ListGridField brandGridField = new ListGridField("brand", "Marca");
		brandGridField.setAlign(Alignment.CENTER);
		// estadoCambioNuevas.setPrompt("Estado de la solicitud de cambio");
		// estadoCambioNuevas.setCellFormatter(new CellFormatter() {
		// @Override
		// public String format(Object value, ListGridRecord record,
		// int rowNum, int colNum) {
		// if (value != null) {
		// try {
		// int estado = (Integer) value;
		// switch (estado) {
		// case 1:
		// return "Nuevo";
		// case 2:
		// return "Aceptado";
		// case 3:
		// return "Rechazado";
		// case 4:
		// return "Aprobado";
		// case 5:
		// return "No aprobado";
		// case 6:
		// return "En construcción";
		// case 7:
		// return "En prueba";
		// case 8:
		// return "En implementación";
		// case 9:
		// return "Finalizado";
		// case 11:
		// return "Aprobado Jcc";
		// case 12:
		// return "No aprobado Jcc";
		// case 13:
		// return "Atendido";
		// default:
		// return "";
		// }
		// } catch (Exception e) {
		// return value.toString() + ": " + e;
		// }
		// }
		// return "";
		// }
		// });

		ListGridField modelGridField = new ListGridField("model", "Modelo");
		modelGridField.setAlign(Alignment.CENTER);
		// anaAsignadoCambioNuevas.setPrompt("Analista asignado");
		// anaAsignadoCambioNuevas.setEmptyCellValue("No asignado");

		ListGridField makeGridField = new ListGridField("manufacturer",
				"Fabricante");
		makeGridField.setAlign(Alignment.CENTER);
		// ListGridFieldDate fechaReportado = new ListGridFieldDate("fechaSDC",
		// "Reportado", 89);
		// fechaReportado.setAlign(Alignment.CENTER);
		// fechaReportado.setPrompt("Fecha de reporte de la solicitud de cambio");

		setFields(idGridField, codeGridField, nameGridField, brandGridField,
				modelGridField, makeGridField);

		loadData();
	}

	private void loadData() {
		EIATypeModel.getAll(new GHAAsyncCallback<List<EiaType>>() {

			@Override
			public void onSuccess(List<EiaType> eiaTypes) {
				ListGridRecord[] array = (ListGridRecord[]) EIAUtil
						.toGridRecords(eiaTypes).toArray(new EIARecord[] {});
				setData(array);
			}
		});

	}
}
