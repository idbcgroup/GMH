package org.fourgeeks.gha.webclient.client.eia.reports;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHACheckboxItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHARadioGroupItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitleTextItem;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class EIAReportListEiaTypesEiasForm extends GHAReportForm {
	private static final String VAL_SOME_EIATYPES = "someEiaTypes";
	private static final String VAL_ALL_EIATYPES = "allEiaTypes";

	private GHATitleTextItem filtersTitleItem;
	private GHARadioGroupItem filtersRadioGroupItem;
	private GHACheckboxItem showEiasCheckboxItem;
	private GHASelectItem eiaTypesSelectItem, facilsSelectItem, workAreasSelectItem,
			eiaStatesSelectItem;

	public EIAReportListEiaTypesEiasForm() {
		DynamicForm formFilters = new DynamicForm();
		formFilters.setTitleOrientation(TitleOrientation.TOP);
		formFilters.setNumCols(1);

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(2);

		// CREANDO LOS ITEMS
		filtersTitleItem = new GHATitleTextItem("Filtros");

		filtersRadioGroupItem = new GHARadioGroupItem(300, false);

		eiaTypesSelectItem = new GHASelectItem("Tipo de equipo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eiaTypesSelectItem.setMultiple(true);

		showEiasCheckboxItem = new GHACheckboxItem("Mostar equipos por tipo de equipo", true);
		showEiasCheckboxItem.setColSpan(2);

		workAreasSelectItem = new GHASelectItem("Area de Trabajo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		workAreasSelectItem.setDisabled(true);
		workAreasSelectItem.setMultiple(true);

		facilsSelectItem = new GHASelectItem("Facilidad", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		facilsSelectItem.setDisabled(true);
		facilsSelectItem.setMultiple(true);

		eiaStatesSelectItem = new GHASelectItem("Estado", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eiaStatesSelectItem.setDisabled(true);

		// DEFINIENDO MANEJADORES DE EVENTOS
		filtersRadioGroupItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				String filterType = filtersRadioGroupItem.getValueAsString();

				if (filterType.equals(VAL_SOME_EIATYPES))
					eiaTypesSelectItem.setDisabled(false);
				else if (filterType.equals(VAL_ALL_EIATYPES))
					eiaTypesSelectItem.setDisabled(true);
			}
		});

		showEiasCheckboxItem.addChangedHandler(new ChangedHandler() {
			private void disableItems(boolean facil, boolean workArea, boolean eiaState) {
				facilsSelectItem.setDisabled(facil);
				workAreasSelectItem.setDisabled(workArea);
				eiaStatesSelectItem.setDisabled(eiaState);
			}

			@Override
			public void onChanged(ChangedEvent event) {
				boolean showEias = showEiasCheckboxItem.getValueAsBoolean();

				if (showEias)
					disableItems(false, false, false);
				else
					disableItems(true, true, true);
			}
		});

		// LLENANDO CON DATOS LOS ITEMS
		fillItemsWhitData();

		// ASIGNANDO LOS ITEMS AL FORM Y DEVOLVIENDO EL LAYOUT
		formFilters.setItems(filtersTitleItem, filtersRadioGroupItem);

		form.setItems(eiaTypesSelectItem, new GHASpacerItem(), new GHASpacerItem(2),
				showEiasCheckboxItem, facilsSelectItem, workAreasSelectItem, eiaStatesSelectItem);

		addMembers(formFilters, form);
	}

	/**
	 * Limpia los valores de los items del formulario
	 */
	public void cleanItems() {
		filtersRadioGroupItem.clearValue();
		eiaTypesSelectItem.clearValue();
		facilsSelectItem.clearValue();
		workAreasSelectItem.clearValue();
		eiaStatesSelectItem.clearValue();
		showEiasCheckboxItem.clearValue();

		eiaTypesSelectItem.setDisabled(false);
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
		map.put(VAL_SOME_EIATYPES, "Uno o varios tipos de equipo");
		map.put(VAL_ALL_EIATYPES, "Todos los tipos de equipo");
		filtersRadioGroupItem.setValueMap(map);
		filtersRadioGroupItem.setDefaultValue(VAL_SOME_EIATYPES);

		eiaStatesSelectItem.setValueMap(EiaStateEnum.toValueMap());

		showEiasCheckboxItem.setDefaultValue(false);

		GHACache.INSTANCE.getEiaTypes(new GHAAsyncCallback<List<EiaType>>() {
			@Override
			public void onSuccess(List<EiaType> result) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				for (EiaType entity : result)
					map.put(entity.getCode(), entity.getName());
				eiaTypesSelectItem.setValueMap(map);
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
		String url = "reports/eia/leia?";

		String selectedFilter = filtersRadioGroupItem.getValueAsString();
		Boolean showEias = showEiasCheckboxItem.getValueAsBoolean();

		if (selectedFilter.equals(VAL_ALL_EIATYPES)) // todos los EIA_TYPE
			url = buildUrl(url, "eiatypes", "all");
		else {
			// algunos EIA_TYPE (los seleccionados)
			String[] eiaTypes = eiaTypesSelectItem.getValues();
			if (eiaTypes.length > 0)
				url = buildUrl(url, "eiatypes", toCommaRepresent(eiaTypes));
		}

		// mostrar EIAs
		url = buildUrl(url, "showeias", showEias.toString());

		if (showEias) {
			// facilidades
			String[] facils = facilsSelectItem.getValues();
			if (facils.length > 0)
				url = buildUrl(url, "facils", toCommaRepresent(facils));
			// areas de trabajo
			String[] workAreas = workAreasSelectItem.getValues();
			if (workAreas.length > 0)
				url = buildUrl(url, "workareas", toCommaRepresent(workAreas));
			// estado del equipo
			String eiaState = eiaStatesSelectItem.getValueAsString();
			String valEdoEiaParam = (eiaState != null) ? eiaState : "all";
			url = buildUrl(url, "edoeia", valEdoEiaParam);
		}

		// usuario registrado (operador)
		Citizen user = GHASessionData.getLoggedUser().getCitizen();
		String loggedUser = user.getFirstName() + " " + user.getFirstLastName();
		url = buildUrl(url, "user", loggedUser);

		return url;
	}
}
