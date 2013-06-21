package org.fourgeeks.gha.webclient.client.eiatype.caracteristicas;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIATypeCaracteristicasSubTab extends Tab implements
		EIATypeSelectionListener {

	private EIATypeCaracteristicasFormPanel form;

	public EIATypeCaracteristicasSubTab() {
		setTitle("Caracteristicas");
		setPaneMargin(0);
		form = new EIATypeCaracteristicasFormPanel();
		setPane(form);
	}

	@Override
	public void select(EiaType eiaType) {
		form.select(eiaType);
	}

}
