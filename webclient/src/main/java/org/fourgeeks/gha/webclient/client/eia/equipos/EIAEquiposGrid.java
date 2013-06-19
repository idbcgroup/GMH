package org.fourgeeks.gha.webclient.client.eia.equipos;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class EIAEquiposGrid extends ListGrid {

	public EIAEquiposGrid() {
		setWidth100();
		setHeight("300px");
		setEmptyMessage("No existen equipos para mostrar.");

		setAlternateRecordStyles(false);
		setCanResizeFields(false);
		setShowFilterEditor(true);

		ListGridField idGridField = new ListGridField("id", "No");
		idGridField.setAlign(Alignment.CENTER);

		ListGridField codeGridField = new ListGridField("code", "Código");
		codeGridField.setAlign(Alignment.CENTER);
		
		ListGridField serialGridField = new ListGridField("serial", "Serial");
		serialGridField.setAlign(Alignment.CENTER);
		
		ListGridField nameGridField = new ListGridField("name", "Nombre");
		nameGridField.setAlign(Alignment.CENTER);
		
		ListGridField facilityGridField = new ListGridField("facility", "Ubicación");
		facilityGridField.setAlign(Alignment.CENTER);

		ListGridField statusGridField = new ListGridField("status", "Status");
		statusGridField.setAlign(Alignment.CENTER);

		setFields(idGridField, codeGridField, serialGridField, facilityGridField, statusGridField);

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
