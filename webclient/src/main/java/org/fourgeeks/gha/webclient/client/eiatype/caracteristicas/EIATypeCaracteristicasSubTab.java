package org.fourgeeks.gha.webclient.client.eiatype.caracteristicas;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIATypeCaracteristicasSubTab extends Tab implements
		EIATypeSelectionListener {

	private EIATypeCaracteristicasForm form;

	public EIATypeCaracteristicasSubTab() {
		setTitle("Caracteristicas");
		setPaneMargin(0);
		form = new EIATypeCaracteristicasForm();
		setPane(form);
	}

	@Override
	public void select(EiaType eiaType) {
		// EIAModel.find(eiaType, new GHAAsyncCallback<List<Eia>>() {
		//
		// @Override
		// public void onSuccess(List<Eia> eias) {
		//
		// ListGridRecord[] array = EIAUtil.toGridRecords(eias).toArray(
		// new EIATypeRecord[] {});
		// caracteristicasGridPanel.setData(array);
		//
		// }
		// });
	}

}
