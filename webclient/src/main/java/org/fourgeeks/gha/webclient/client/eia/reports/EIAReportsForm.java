package org.fourgeeks.gha.webclient.client.eia.reports;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaReportFiltersEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHARadioGroupItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAReportsForm extends VLayout {
	private GHARadioGroupItem filterTypeRadioGroupItem;
	private GHASelectItem estadoSelectItem, facilitySelectItem,
			workingAreaSelectItem, tipoAgrupSelectItem;

	public EIAReportsForm() {
		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(1);

		// CREANDO LOS ITEMS
		filterTypeRadioGroupItem = new GHARadioGroupItem(350, false);

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

		filterTypeRadioGroupItem.addChangedHandler(new ChangedHandler() {
			private void disableItems(boolean edoEq, boolean fac, boolean workAr) {
				estadoSelectItem.setDisabled(edoEq);
				facilitySelectItem.setDisabled(fac);
				workingAreaSelectItem.setDisabled(workAr);
			}

			@Override
			public void onChanged(ChangedEvent event) {
				EiaReportFiltersEnum filterType = EiaReportFiltersEnum
						.valueOf(filterTypeRadioGroupItem.getValueAsString());

				if (filterType == EiaReportFiltersEnum.EDO_EQUIPO)
					disableItems(false, true, true);
				else if (filterType == EiaReportFiltersEnum.FACILITY)
					disableItems(true, false, true);
				else if (filterType == EiaReportFiltersEnum.WORKING_AREA)
					disableItems(true, true, false);
			}
		});

		// LLENANDO CON DATOS LOS ITEMS
		fillItemsWhitData();

		// ASIGNANDO LOS ITEMS AL FORM Y DEVOLVIENDO EL LAYOUT
		form.setItems(filterTypeRadioGroupItem, estadoSelectItem,
				workingAreaSelectItem, facilitySelectItem, tipoAgrupSelectItem);

		addMember(form);
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

	/**
	 * Llena con datos los diferentes items del formulario
	 */
	private void fillItemsWhitData() {
		LinkedHashMap<String, String> map = EiaReportFiltersEnum.toValueMap();
		map.remove(EiaReportFiltersEnum.SIN_FILTRO.name());
		String edoEquipoName = EiaReportFiltersEnum.EDO_EQUIPO.name();
		filterTypeRadioGroupItem.setValueMap(map);
		filterTypeRadioGroupItem.setDefaultValue(edoEquipoName);

		map = new LinkedHashMap<String, String>();
		map.put(getClassName(EiaStateEnum.class), "Estado del equipo");
		map.put(getClassName(Facility.class), "Facilidad");
		map.put(getClassName(WorkingArea.class), "Area de Trabajo");
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
	 * @param clase
	 *            la clase de la que se desea obtener el nombre
	 * @return El nombre de la clase
	 */
	private String getClassName(Class<?> clase) {
		return clase.getName();
	}

	/**
	 * @return La parte del URL que contiene los parametros del reporte
	 */
	public String getURLParameters() {
		Citizen user = GHASessionData.getLoggedUser().getCitizen();
		String loggedUser = user.getFirstName() + " " + user.getFirstLastName();

		String group = tipoAgrupSelectItem.getValueAsString();
		String groupDesc = tipoAgrupSelectItem.getDisplayValue();

		EiaReportFiltersEnum filterType = EiaReportFiltersEnum
				.valueOf(filterTypeRadioGroupItem.getValueAsString());

		String filterDesc = null, filterVal = null;
		if (filterType == EiaReportFiltersEnum.EDO_EQUIPO) {
			filterDesc = estadoSelectItem.getDisplayValue();
			filterVal = estadoSelectItem.getValueAsString();

		} else if (filterType == EiaReportFiltersEnum.FACILITY) {
			filterDesc = facilitySelectItem.getDisplayValue();
			filterVal = facilitySelectItem.getValueAsString();

		} else if (filterType == EiaReportFiltersEnum.WORKING_AREA) {
			filterDesc = workingAreaSelectItem.getDisplayValue();
			filterVal = workingAreaSelectItem.getValueAsString();
		}

		if (filterVal == null) {
			filterType = EiaReportFiltersEnum.SIN_FILTRO;
			filterVal = "all";
			filterDesc = "Todos";
		}

		String urlParams = "?";
		urlParams += "filtertype=" + filterType.name();
		urlParams += "&filtertypedesc=" + filterType.toString();
		urlParams += "&filter=" + filterVal;
		urlParams += "&filterdesc=" + filterDesc;
		urlParams += "&group=" + group;
		urlParams += "&groupdesc=" + groupDesc;
		urlParams += "&user=" + loggedUser;

		return urlParams;
	}

}
