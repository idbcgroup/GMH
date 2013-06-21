package org.fourgeeks.gha.webclient.client.eiatype.equipos;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eia.EIAModel;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeRecord;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class EIATypeEquiposGrid extends ListGrid implements
		EIATypeSelectionListener {

	public EIATypeEquiposGrid() {
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setEmptyMessage("No existen tipos de equipo para mostrar");
		setAlternateRecordStyles(false);
		setCanResizeFields(false);

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField codeGridField = new GHAGridField("code", "Codigo");
		GHAGridField nameGridField = new GHAGridField("name", "Nombre");
		GHAGridField brandGridField = new GHAGridField("brand", "Marca");
		GHAGridField modelGridField = new GHAGridField("model", "Modelo");
		GHAGridField makeGridField = new GHAGridField("manufacturer",
				"Fabricante");

		setFields(idGridField, codeGridField, nameGridField, brandGridField,
				modelGridField, makeGridField);
	}

	@Override
	public void select(EiaType eiaType) {
		EIAModel.find(eiaType, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> result) {
				ListGridRecord[] array = (ListGridRecord[]) EIAUtil
						.toGridRecords(result).toArray(new EIATypeRecord[] {});
				setData(array);

			}
		});

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
