package org.fourgeeks.gha.webclient.client.eia.reports;

import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHARadioGroupItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitleTextItem;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class EIAReportDetalleEquiposForm extends GHAReportForm {
	private static final String VAL_SOME_EIAS = "someEias";
	private static final String VAL_ALL_EIAS = "allEias";
	private static final String VAL_ORDER_BY_UBIC_EIATYPE = "true";
	private static final String VAL_ORDER_BY_EIATYPE_UBIC = "false";

	private GHATitleTextItem filtersTitleItem, orderByTitleItem;
	private GHARadioGroupItem filtersRadioGroupItem;
	private GHARadioGroupItem orderByRadioGroupItem;
	private GHASelectItem eiasSelectItem, facilsSelectItem,
			workAreasSelectItem, eiaStatesSelectItem;

	public EIAReportDetalleEquiposForm() {
		DynamicForm formFilters = new DynamicForm();
		formFilters.setTitleOrientation(TitleOrientation.TOP);
		formFilters.setNumCols(1);

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(3);

		DynamicForm formOrderBy = new DynamicForm();
		formOrderBy.setTitleOrientation(TitleOrientation.TOP);
		formOrderBy.setNumCols(1);

		// CREANDO LOS ITEMS
		filtersTitleItem = new GHATitleTextItem("Filtros");

		filtersRadioGroupItem = new GHARadioGroupItem(300, false);

		eiasSelectItem = new GHASelectItem("Equipo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eiasSelectItem.setMultiple(true);

		workAreasSelectItem = new GHASelectItem("Area de Trabajo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		workAreasSelectItem.setDisabled(true);
		workAreasSelectItem.setMultiple(true);

		facilsSelectItem = new GHASelectItem("Facilidad",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		facilsSelectItem.setDisabled(true);
		facilsSelectItem.setMultiple(true);

		eiaStatesSelectItem = new GHASelectItem("Estado",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eiaStatesSelectItem.setDisabled(true);

		orderByTitleItem = new GHATitleTextItem("Ordenar por");

		orderByRadioGroupItem = new GHARadioGroupItem(350, false);

		// DEFINIENDO MANEJADORES DE EVENTOS
		filtersRadioGroupItem.addChangedHandler(new ChangedHandler() {
			private void disableItems(boolean eias, boolean facil,
					boolean workArea, boolean eiaState) {
				eiasSelectItem.setDisabled(eias);
				facilsSelectItem.setDisabled(facil);
				workAreasSelectItem.setDisabled(workArea);
				eiaStatesSelectItem.setDisabled(eiaState);
			}

			@Override
			public void onChanged(ChangedEvent event) {
				String filterType = filtersRadioGroupItem.getValueAsString();

				if (filterType.equals(VAL_SOME_EIAS))
					disableItems(false, true, true, true);
				else if (filterType.equals(VAL_ALL_EIAS))
					disableItems(true, false, false, false);
			}
		});

		// LLENANDO CON DATOS LOS ITEMS
		fillItemsWhitData();

		// ASIGNANDO LOS ITEMS AL FORM Y DEVOLVIENDO EL LAYOUT
		formFilters.setItems(filtersTitleItem, filtersRadioGroupItem);

		form.setItems(eiasSelectItem, new GHASpacerItem(2), facilsSelectItem,
				workAreasSelectItem, new GHASpacerItem(), eiaStatesSelectItem);

		formOrderBy.setItems(orderByTitleItem, orderByRadioGroupItem);

		addMembers(formFilters, form, formOrderBy);
	}

	/**
	 * Limpia los valores de los items del formulario
	 */
	public void cleanItems() {
		eiasSelectItem.clearValue();
		facilsSelectItem.clearValue();
		workAreasSelectItem.clearValue();
		eiaStatesSelectItem.clearValue();
	}

	/**
	 * Llena con datos los diferentes items del formulario
	 */
	private void fillItemsWhitData() {
		LinkedHashMap<String, String> map;

		map = new LinkedHashMap<String, String>();
		map.put(VAL_SOME_EIAS, "Uno o varios equipos");
		map.put(VAL_ALL_EIAS, "Todos los equipos");
		filtersRadioGroupItem.setValueMap(map);
		filtersRadioGroupItem.setDefaultValue(VAL_SOME_EIAS);

		map = new LinkedHashMap<String, String>();
		map.put(VAL_ORDER_BY_UBIC_EIATYPE, "Ubicación y tipo de equipo");
		map.put(VAL_ORDER_BY_EIATYPE_UBIC, "Tipo de equipo y ubicación");
		orderByRadioGroupItem.setValueMap(map);
		orderByRadioGroupItem.setDefaultValue(VAL_ORDER_BY_UBIC_EIATYPE);

		eiaStatesSelectItem.setValueMap(EiaStateEnum.toValueMap());

		GHACache.INSTANCE.getEias(new GHAAsyncCallback<List<Eia>>() {
			@Override
			public void onSuccess(List<Eia> result) {
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				for (Eia entity : result)
					map.put(entity.getId() + "", entity.getCode());
				eiasSelectItem.setValueMap(map);
			}
		});

		GHACache.INSTANCE
				.getWorkingAreas(new GHAAsyncCallback<List<WorkingArea>>() {
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
		String url = "reports/eia/feia?";

		String selectedFilter = filtersRadioGroupItem.getValueAsString();

		// algunos EIA (los seleccionados)
		if (selectedFilter.equals(VAL_SOME_EIAS)) {
			String[] eias = eiasSelectItem.getValues();
			if (eias.length > 0)
				url = buildUrl(url, "eias", toCommaRepresent(eias));
		}
		// todos los EIA
		else {
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

		// orden del equipo
		String orderBy = orderByRadioGroupItem.getValueAsString();
		url = buildUrl(url, "orden", orderBy);

		return url;
	}

	/**
	 * Append query param into a url
	 * 
	 * @param url
	 *            the URL String for what the query param is going be append
	 * @param param
	 *            the param of the query param
	 * @param value
	 *            the value of the query param
	 * @return the URL String with the appended query param
	 */
	private String buildUrl(String url, String param, String value) {
		char lastchar = url.charAt(url.length() - 1);
		if (lastchar == '?')
			url += param + "=" + value;
		else
			url += "&" + param + "=" + value;

		return url;
	}

	/**
	 * Transform a String Array into a String of comma-separated values
	 * 
	 * @param valsParam
	 *            the String Array to transform
	 * @return The comma-separated String
	 */
	private String toCommaRepresent(String[] valsParam) {
		String commaRepresentation = "";
		for (int i = 0; i < valsParam.length; i++) {
			commaRepresentation += valsParam[i];
			if (i < valsParam.length - 1)
				commaRepresentation += ",";
		}
		return commaRepresentation;
	}
}
