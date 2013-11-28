package org.fourgeeks.gha.webclient.client.eia.reports;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHARadioGroupItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitletextItem;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class EIAReportListEiasForm extends GHAReportForm {
	private static final String VAL_ONE_EIA = "oneEia";
	private static final String VAL_SOME_EIAS = "someEias";
	private static final String VAL_ALL_EIAS = "allEias";
	private static final String VAL_ORDER_BY_UBIC_EIA = "true";
	private static final String VAL_ORDER_BY_EIA_UBIC = "false";

	private GHATitletextItem filtersTitleItem, orderByTitleItem;
	private GHARadioGroupItem filtersRadioGroupItem;
	private GHARadioGroupItem orderByRadioGroupItem;
	private GHASelectItem eiaSelectItem, eiaTypeSelectItem, facilsSelectItem, workAreasSelectItem,
			eiaStatesSelectItem;

	public EIAReportListEiasForm() {

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(2);

		// CREANDO LOS ITEMS
		filtersTitleItem = new GHATitletextItem("Filtros");
		filtersTitleItem.setColSpan(2);

		filtersRadioGroupItem = new GHARadioGroupItem(360, false);
		filtersRadioGroupItem.setColSpan(2);

		eiaSelectItem = new GHASelectItem("Equipo", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);

		eiaTypeSelectItem = new GHASelectItem("Tipo", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eiaTypeSelectItem.setDisabled(true);

		workAreasSelectItem = new GHASelectItem("Area de Trabajo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		workAreasSelectItem.setDisabled(true);
		workAreasSelectItem.setMultiple(true);

		facilsSelectItem = new GHASelectItem("Facilidad", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		facilsSelectItem.setDisabled(true);
		facilsSelectItem.setMultiple(true);

		eiaStatesSelectItem = new GHASelectItem("Estado", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eiaStatesSelectItem.setDisabled(true);

		orderByTitleItem = new GHATitletextItem("Ordenar por");
		orderByTitleItem.setColSpan(2);

		orderByRadioGroupItem = new GHARadioGroupItem(250, false);
		orderByRadioGroupItem.setColSpan(2);

		// DEFINIENDO MANEJADORES DE EVENTOS
		filtersRadioGroupItem.addChangedHandler(new ChangedHandler() {
			private void disableItems(boolean eia, boolean eiaType, boolean facil,
					boolean workArea, boolean eiaState) {
				eiaSelectItem.setDisabled(eia);
				eiaTypeSelectItem.setDisabled(eiaType);
				facilsSelectItem.setDisabled(facil);
				workAreasSelectItem.setDisabled(workArea);
				eiaStatesSelectItem.setDisabled(eiaState);
			}

			@Override
			public void onChanged(ChangedEvent event) {
				String filterType = filtersRadioGroupItem.getValueAsString();

				if (filterType.equals(VAL_ONE_EIA))
					disableItems(false, true, true, true, true);
				else if (filterType.equals(VAL_SOME_EIAS))
					disableItems(true, false, false, false, false);
				else if (filterType.equals(VAL_ALL_EIAS))
					disableItems(true, true, false, false, false);
			}
		});

		// LLENANDO CON DATOS LOS ITEMS
		fillItemsWhitData();

		// ASIGNANDO LOS ITEMS AL FORM Y DEVOLVIENDO EL LAYOUT
		form.setItems(filtersTitleItem, filtersRadioGroupItem, eiaSelectItem, eiaTypeSelectItem,
				facilsSelectItem, workAreasSelectItem, eiaStatesSelectItem, new GHASpacerItem(),
				orderByTitleItem, orderByRadioGroupItem);

		addMembers(form);
	}

	/**
	 * Limpia los valores de los items del formulario
	 */
	public void cleanItems() {
		eiaSelectItem.clearValue();
		eiaTypeSelectItem.clearValue();
		facilsSelectItem.clearValue();
		workAreasSelectItem.clearValue();
		eiaStatesSelectItem.clearValue();
		filtersRadioGroupItem.clearValue();
		orderByRadioGroupItem.clearValue();

		eiaSelectItem.setDisabled(false);
		eiaTypeSelectItem.setDisabled(true);
		facilsSelectItem.setDisabled(true);
		workAreasSelectItem.setDisabled(true);
		eiaStatesSelectItem.setDisabled(true);
	}

	/**
	 * Llena con datos los diferentes items del formulario
	 */
	private void fillItemsWhitData() {
		LinkedHashMap<String, String> map;

		map = new LinkedHashMap<String, String>();
		map.put(VAL_ONE_EIA, "Uno equipo");
		map.put(VAL_SOME_EIAS, "varios equipos (por tipo)");
		map.put(VAL_ALL_EIAS, "Todos los equipos");
		filtersRadioGroupItem.setValueMap(map);
		filtersRadioGroupItem.setDefaultValue(VAL_ONE_EIA);

		map = new LinkedHashMap<String, String>();
		map.put(VAL_ORDER_BY_UBIC_EIA, "Ubicación y equipo");
		map.put(VAL_ORDER_BY_EIA_UBIC, "Equipo y ubicación");
		orderByRadioGroupItem.setValueMap(map);
		orderByRadioGroupItem.setDefaultValue(VAL_ORDER_BY_UBIC_EIA);

		eiaStatesSelectItem.setValueMap(EiaStateEnum.toValueMap());

		GHACache.INSTANCE.getEias(new GHAAsyncCallback<List<Eia>>() {
			@Override
			public void onSuccess(List<Eia> result) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				for (Eia entity : result)
					map.put(entity.getId() + "", entity.getCode());
				eiaSelectItem.setValueMap(map);
			}
		});

		GHACache.INSTANCE.getEiaTypes(new GHAAsyncCallback<List<EiaType>>() {
			@Override
			public void onSuccess(List<EiaType> result) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				for (EiaType entity : result)
					map.put(entity.getCode(), entity.getName());
				eiaTypeSelectItem.setValueMap(map);
			}
		}, false);

		GHACache.INSTANCE.getWorkingAreas(new GHAAsyncCallback<List<WorkingArea>>() {
			@Override
			public void onSuccess(List<WorkingArea> result) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				for (WorkingArea entity : result)
					map.put(entity.getId() + "", entity.getName());
				workAreasSelectItem.setValueMap(map);
			}
		});

		GHACache.INSTANCE.getFacilities(new GHAAsyncCallback<List<Facility>>() {
			@Override
			public void onSuccess(List<Facility> result) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				for (Facility entity : result)
					map.put(entity.getId() + "", entity.getName());
				facilsSelectItem.setValueMap(map);
			}
		});
	}

	@Override
	public String getReportURI() {
		String url = "reports/eia/leqi?";

		Citizen user = GHASessionData.getLoggedUser().getCitizen();
		String loggedUser = user.getFirstName() + " " + user.getFirstLastName();

		String selectedFilter = filtersRadioGroupItem.getValueAsString();
		String eia = eiaSelectItem.getValueAsString();
		String eiaType = eiaTypeSelectItem.getValueAsString();
		String[] facils = facilsSelectItem.getValues();
		String[] workAreas = workAreasSelectItem.getValues();
		String eiaState = eiaStatesSelectItem.getValueAsString();
		String orderBy = orderByRadioGroupItem.getValueAsString();

		// un Eia (el seleccionado)
		if (selectedFilter.equals(VAL_ONE_EIA) && eia != null)
			url = buildUrl(url, "eia", eia);
		else {
			// algunos Eia (por tipo)
			if (selectedFilter.equals(VAL_SOME_EIAS) && eiaType != null)
				url = buildUrl(url, "eiatype", eiaType);
			else
				url = buildUrl(url, "eia", "all"); // todos los Eia

			// facilidades
			if (facils.length > 0)
				url = buildUrl(url, "facils", toCommaRepresent(facils));
			// areas de trabajo
			if (workAreas.length > 0)
				url = buildUrl(url, "workareas", toCommaRepresent(workAreas));
			// estado del equipo
			String valEdoEiaParam = (eiaState != null) ? eiaState : "all";
			url = buildUrl(url, "edoeia", valEdoEiaParam);
		}

		// usuario registrado (operador)
		url = buildUrl(url, "user", loggedUser);
		// orden del equipo
		url = buildUrl(url, "orden", orderBy);

		return url;
	}
}
