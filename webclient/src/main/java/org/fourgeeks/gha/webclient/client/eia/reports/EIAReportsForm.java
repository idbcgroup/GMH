package org.fourgeeks.gha.webclient.client.eia.reports;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHARadioGroupItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAReportsForm extends VLayout {
	private GHARadioGroupItem filtroReportRadioGroupItem;
	private GHASelectItem estadoSelectItem, facilitySelectItem,
			workingAreaSelectItem, tipoAgrupSelectItem;

	public EIAReportsForm() {
		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(1);

		// CREANDO LOS ITEMS
		filtroReportRadioGroupItem = new GHARadioGroupItem(350, false);

		estadoSelectItem = new GHASelectItem("Estado",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		estadoSelectItem.setDisabled(false);

		workingAreaSelectItem = new GHASelectItem("Area de Trabajo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		workingAreaSelectItem.setDisabled(true);

		facilitySelectItem = new GHASelectItem("Servicio/Instalación",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		facilitySelectItem.setDisabled(true);

		tipoAgrupSelectItem = new GHASelectItem("Agrupar por",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		tipoAgrupSelectItem.setDisabled(false);

		// DEFINIENDO LOS EVENTOS DE LOS ITEMS
		filtroReportRadioGroupItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				final String filtroReportValue = filtroReportRadioGroupItem
						.getValueAsString();

				if (filtroReportValue.equals("edoEquipo")) {
					estadoSelectItem.setDisabled(false);
					facilitySelectItem.setDisabled(true);
					workingAreaSelectItem.setDisabled(true);

				} else if (filtroReportValue.equals("facility")) {
					estadoSelectItem.setDisabled(true);
					facilitySelectItem.setDisabled(true);
					workingAreaSelectItem.setDisabled(false);

				} else {
					estadoSelectItem.setDisabled(true);
					facilitySelectItem.setDisabled(false);
					workingAreaSelectItem.setDisabled(true);

				}
			}
		});

		// LLENANDO CON DATOS LOS ITEMS
		fillItemsWhitData();

		// ASIGNANDO LOS ITEMS AL FORM Y DEVOLVIENDO EL LAYOUT
		form.setItems(filtroReportRadioGroupItem, estadoSelectItem,
				workingAreaSelectItem, facilitySelectItem, tipoAgrupSelectItem);

		addMember(form);
	}

	/**
	 * Llena con datos los diferentes items del formulario
	 */
	private void fillItemsWhitData() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("edoEquipo", "Estado Equipo");
		map.put("workingArea", "Area de Trabajo");
		map.put("facility", "Servicio/Instalación");
		filtroReportRadioGroupItem.setValueMap(map);
		filtroReportRadioGroupItem.setDefaultValue("edoEquipo");

		map = new LinkedHashMap<String, String>();
		map.put("edoEquipo", "Estado Equipo");
		map.put("facility", "Servicio/Instalación");
		map.put("workingArea", "Area de Trabajo");
		map.put("noAgrup", "Sin agrupar");
		tipoAgrupSelectItem.setValueMap(map);
		tipoAgrupSelectItem.setDefaultValue("noAgrup");

		estadoSelectItem.setValueMap(EiaStateEnum.toValueMap());

		GHACache.INSTANCE
				.getWorkingAreas(new GHAAsyncCallback<List<WorkingArea>>() {
					@Override
					public void onSuccess(List<WorkingArea> result) {
						LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
						for (WorkingArea entity : result)
							map.put(entity.getId() + "", entity.getName());
						workingAreaSelectItem.setValueMap(map);
					}
				});

		GHACache.INSTANCE.getFacilities(new GHAAsyncCallback<List<Facility>>() {
			@Override
			public void onSuccess(List<Facility> result) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				for (Facility entity : result)
					map.put(entity.getId() + "", entity.getName());
				facilitySelectItem.setValueMap(map);
			}
		});
	}

	/**
	 * Limpia los valores de los items del formulario
	 */
	public void cleanItems() {
		estadoSelectItem.clearValue();
		facilitySelectItem.clearValue();
		workingAreaSelectItem.clearValue();
		tipoAgrupSelectItem.clearValue();
	}

}
