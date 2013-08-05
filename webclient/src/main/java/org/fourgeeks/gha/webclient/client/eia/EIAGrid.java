package org.fourgeeks.gha.webclient.client.eia;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAGrid;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class EIAGrid extends GHAGrid<Eia> implements ResizeHandler {

	public EIAGrid() {
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getGridSize(30));
		setEmptyMessage("No existen tipos de equipo para mostrar");
		setAlternateRecordStyles(false);
		setCanResizeFields(false);

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField codeGridField = new GHAGridField("code", "Codigo");
		GHAGridField serialGridField = new GHAGridField("serialNumber",
				"Serial");

		GHAGridField nameGridField = new GHAGridField("eiaTypeName", "Nombre");
		GHAGridField brandGridField = new GHAGridField("brandName", "Marca");
		GHAGridField modelGridField = new GHAGridField("eiaTypeModel", "Modelo");
		GHAGridField makeGridField = new GHAGridField("manufacturerName",
				"Fabricante");

		setFields(idGridField, codeGridField, serialGridField, nameGridField,
				brandGridField, modelGridField, makeGridField);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getGridSize(30));
	}

	@Override
	public void load(List<Eia> list) {
		ListGridRecord[] array = (ListGridRecord[]) EIAUtil.toGridRecords(list)
				.toArray(new EIARecord[] {});
		setData(array);
	}

	@Override
	public Eia getSelectedEntity() {
		ListGridRecord selectedRecord = getSelectedRecord();
		if (selectedRecord != null)
			return ((EIARecord) selectedRecord).toEntity();
		return null;
	}

	@Override
	public EIARecord getSelectedRecord() {
		ListGridRecord selectedRecord = getSelectedRecord();
		if (selectedRecord != null)
			return (EIARecord) selectedRecord;
		return null;
	}
}
