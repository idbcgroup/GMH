package org.fourgeeks.gha.webclient.client.eia.reports;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaReportFiltersEnum;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHARadioGroupItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitletextItem;
import org.fourgeeks.gha.webclient.client.eiatype.component.EIATypeComponentModel;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class EIAReportListEiaTypeCompsForm extends GHAReportForm {
	private static final String VAL_ORDER_BY_EIATYPE_AND_COMPS = "true";
	private static final String VAL_ORDER_BY_COMPS_AND_EIATYPE = "false";

	private GHATitletextItem filtersTitleItem, orderByTitleItem;
	private GHARadioGroupItem filtersRadioGroupItem;
	private GHARadioGroupItem orderByRadioGroupItem;
	private GHASelectItem eiaTypesSelectItem, eiaTypeSelectItem, eiaTypesComponentSelectItem,
			eiaTypeComponentSelectItem;

	public EIAReportListEiaTypeCompsForm() {

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(2);

		// CREANDO LOS ITEMS
		filtersTitleItem = new GHATitletextItem("Filtros");
		filtersTitleItem.setColSpan(2);

		filtersRadioGroupItem = new GHARadioGroupItem(370, false);
		filtersRadioGroupItem.setColSpan(2);

		eiaTypesSelectItem = new GHASelectItem("Tipos", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eiaTypesSelectItem.setMultiple(true);

		eiaTypeSelectItem = new GHASelectItem("Tipo", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eiaTypeSelectItem.setDisabled(true);

		eiaTypesComponentSelectItem = new GHASelectItem("Componente",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eiaTypesComponentSelectItem.setDisabled(true);

		eiaTypeComponentSelectItem = new GHASelectItem("Componente",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eiaTypeComponentSelectItem.setDisabled(true);

		orderByTitleItem = new GHATitletextItem("Ordenar por");
		orderByTitleItem.setColSpan(2);

		orderByRadioGroupItem = new GHARadioGroupItem(350, false);
		orderByRadioGroupItem.setColSpan(2);

		// DEFINIENDO MANEJADORES DE EVENTOS
		filtersRadioGroupItem.addChangedHandler(new ChangedHandler() {
			boolean eiaTypesCompDisabled = true, eiaTypeCompDisabled = true;

			@Override
			public void onChanged(ChangedEvent event) {
				String filterType = filtersRadioGroupItem.getValueAsString();
				EiaReportFiltersEnum filter = EiaReportFiltersEnum.valueOf(filterType);

				if (filter == EiaReportFiltersEnum.EIATYPE_AND_COMPONENTS) {
					eiaTypesSelectItem.setDisabled(false);
					eiaTypesComponentSelectItem.setDisabled(eiaTypesCompDisabled);

					eiaTypeSelectItem.setDisabled(true);
					eiaTypeCompDisabled = eiaTypeComponentSelectItem.isDisabled();
					eiaTypeComponentSelectItem.setDisabled(true);

				} else if (filter == EiaReportFiltersEnum.COMPONENTS_AND_EIA) {
					eiaTypeSelectItem.setDisabled(false);
					eiaTypeComponentSelectItem.setDisabled(eiaTypeCompDisabled);

					eiaTypesSelectItem.setDisabled(true);
					eiaTypesCompDisabled = eiaTypesComponentSelectItem.isDisabled();
					eiaTypesComponentSelectItem.setDisabled(true);
				}
			}
		});

		eiaTypeSelectItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				String eiaTypeCode = eiaTypeSelectItem.getValueAsString();
				if (eiaTypeCode != null) {
					EiaType eiaType = new EiaType(eiaTypeCode);
					fillComponentSelectItem(eiaType, eiaTypeComponentSelectItem);
					eiaTypeComponentSelectItem.setDisabled(false);
				} else
					eiaTypeComponentSelectItem.setDisabled(true);
			}
		});

		eiaTypesSelectItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				String eiaTypeCode = null;

				String[] eiaTypeCodes = eiaTypesSelectItem.getValues();
				if (eiaTypesSelectItem.getValues().length == 1) {
					eiaTypesComponentSelectItem.setDisabled(false);
					eiaTypeCode = eiaTypeCodes[0];
				} else
					eiaTypesComponentSelectItem.setDisabled(true);

				if (eiaTypeCode != null) {
					EiaType eiaType = new EiaType(eiaTypeCode);
					fillComponentSelectItem(eiaType, eiaTypesComponentSelectItem);
				}
			}
		});

		// LLENANDO CON DATOS LOS ITEMS
		fillItemsWhitData();

		// ASIGNANDO LOS ITEMS AL FORM Y DEVOLVIENDO EL LAYOUT
		form.setItems(filtersTitleItem, filtersRadioGroupItem, eiaTypesSelectItem,
				eiaTypesComponentSelectItem, eiaTypeSelectItem, eiaTypeComponentSelectItem,
				orderByTitleItem, orderByRadioGroupItem);

		addMembers(form);
	}

	/**
	 * Limpia los valores de los items del formulario
	 */
	public void cleanItems() {
		eiaTypesSelectItem.clearValue();
		eiaTypesComponentSelectItem.clearValue();
		eiaTypeSelectItem.clearValue();
		eiaTypeComponentSelectItem.clearValue();

		eiaTypesSelectItem.setDisabled(false);
		eiaTypesComponentSelectItem.setDisabled(true);
		eiaTypeSelectItem.setDisabled(true);
		eiaTypeComponentSelectItem.setDisabled(true);
	}

	/**
	 * Llena con datos los diferentes items del formulario
	 */
	private void fillItemsWhitData() {
		LinkedHashMap<String, String> map = EiaReportFiltersEnum.toValueMap(
				EiaReportFiltersEnum.EIATYPE_AND_COMPONENTS,
				EiaReportFiltersEnum.COMPONENTS_AND_EIA);
		filtersRadioGroupItem.setValueMap(map);
		filtersRadioGroupItem.setDefaultValue(EiaReportFiltersEnum.EIATYPE_AND_COMPONENTS.name());

		map = new LinkedHashMap<String, String>();
		map.put(VAL_ORDER_BY_EIATYPE_AND_COMPS, "Tipo de equipo y componentes");
		map.put(VAL_ORDER_BY_COMPS_AND_EIATYPE, "Componentes y tipo de equipo");
		orderByRadioGroupItem.setValueMap(map);
		orderByRadioGroupItem.setDefaultValue(VAL_ORDER_BY_EIATYPE_AND_COMPS);

		GHACache.INSTANCE.getEiaTypes(new GHAAsyncCallback<List<EiaType>>() {
			@Override
			public void onSuccess(List<EiaType> result) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				for (EiaType entity : result)
					map.put(entity.getCode() + "", entity.getName());
				eiaTypesSelectItem.setValueMap(map);
				eiaTypeSelectItem.setValueMap(map);
			}
		}, false);
	}

	private void fillComponentSelectItem(EiaType eiaType, final GHASelectItem selectItem) {
		selectItem.clearValue();
		// TODO colocar ventana de cargando..
		EIATypeComponentModel.findByParentEiaType(eiaType,
				new GHAAsyncCallback<List<EiaTypeComponent>>() {
					@Override
					public void onSuccess(List<EiaTypeComponent> result) {
						LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
						for (EiaTypeComponent entity : result)
							map.put(entity.getId() + "", entity.getEiaType().getName());
						selectItem.setValueMap(map);

					}
				});
	}

	@Override
	public String getReportURI() {
		String url = "reports/eia/lcrd?";

		Citizen user = GHASessionData.getLoggedUser().getCitizen();
		String loggedUser = user.getFirstName() + " " + user.getFirstLastName();

		String selectedFilter = filtersRadioGroupItem.getValueAsString();
		EiaReportFiltersEnum filter = EiaReportFiltersEnum.valueOf(selectedFilter);
		String[] eiaTypes = eiaTypesSelectItem.getValues();
		String eiaTypesComp = eiaTypesComponentSelectItem.getValueAsString();
		String eiaType = eiaTypeSelectItem.getValueAsString();
		String eiaTypeComp = eiaTypeComponentSelectItem.getValueAsString();
		String orderBy = orderByRadioGroupItem.getValueAsString();

		// tipos de equipo y sus componentes
		if (filter == EiaReportFiltersEnum.EIATYPE_AND_COMPONENTS) {
			// seleccione uno o varios tipos de equipo
			if (eiaTypes.length > 0)
				if (eiaTypes.length == 1 && eiaTypesComp != null)
					// seleccione un componente de un tipo de equipo
					url = buildUrl(url, "component", eiaTypesComp);
				else
					// todos los componentes de los eiaType seleccionados
					url = buildUrl(url, "eiatypes", toCommaRepresent(eiaTypes));
			else
				// todos los componentes de todos los tipos de equipo
				url = buildUrl(url, "eiatypes", "all");
		}
		// Componentes y los equipos que lo tienen definido
		else {
			// seleccione un tipo de equipo
			if (eiaType != null) {
				if (eiaTypeComp != null)
					// seleccione un componente de un tipo de equipo
					url = buildUrl(url, "component", eiaTypeComp);
				else
					// todos los equipos de los componentes de ese eiaType
					url = buildUrl(url, "eiatypes", eiaType);
			} else
				// todos los equipos de los componentes de todos los eiaType
				url = buildUrl(url, "eiatypes", "all");
		}

		// tipo de filtro
		url = buildUrl(url, "filter", selectedFilter);

		// usuario registrado (operador)
		url = buildUrl(url, "user", loggedUser);

		// orden de los registros
		url = buildUrl(url, "orden", orderBy);

		return url;
	}
}
