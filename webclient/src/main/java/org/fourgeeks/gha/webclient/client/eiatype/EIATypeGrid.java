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
