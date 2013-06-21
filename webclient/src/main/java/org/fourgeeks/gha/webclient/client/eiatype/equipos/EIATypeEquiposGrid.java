package org.fourgeeks.gha.webclient.client.eiatype.equipos;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class EIATypeEquiposGrid extends ListGrid {

	public EIATypeEquiposGrid() {
		setWidth100();
		setHeight("300px");
		setEmptyMessage("No existen tipos de equipo para mostrar");

		setAlternateRecordStyles(false);
		setCanResizeFields(false);

		ListGridField idGridField = new ListGridField("id", "No");
		idGridField.setAlign(Alignment.CENTER);

		ListGridField codeGridField = new ListGridField("code", "Codigo");
		codeGridField.setAlign(Alignment.CENTER);

		ListGridField nameGridField = new ListGridField("name", "Nombre");
		nameGridField.setAlign(Alignment.CENTER);

		ListGridField brandGridField = new ListGridField("brand", "Marca");
		brandGridField.setAlign(Alignment.CENTER);

		ListGridField modelGridField = new ListGridField("model", "Modelo");
		modelGridField.setAlign(Alignment.CENTER);

		ListGridField makeGridField = new ListGridField("manufacturer",
				"Fabricante");
		makeGridField.setAlign(Alignment.CENTER);

		setFields(idGridField, codeGridField, nameGridField, brandGridField,
				modelGridField, makeGridField);

		// loadData();
	}

	// private void loadData() {
	// EIATypeModel.getAll(new GHAAsyncCallback<List<EiaType>>() {
	//
	// @Override
	// public void onSuccess(List<EiaType> eiaTypes) {
	// ListGridRecord[] array = (ListGridRecord[]) EIAUtil
	// .toGridRecords(eiaTypes)
	// .toArray(new EIATypeRecord[] {});
	// setData(array);
	// }
	// });
	//
	// }
}
