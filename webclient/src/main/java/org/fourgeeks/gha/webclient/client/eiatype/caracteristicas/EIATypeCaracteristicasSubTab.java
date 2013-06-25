package org.fourgeeks.gha.webclient.client.eiatype.caracteristicas;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

import com.smartgwt.client.widgets.tab.Tab;

public class EIATypeCaracteristicasSubTab extends Tab implements
		EIATypeSelectionListener, GHAClosable {

	private EIATypeCaracteristicasFormPanel form;

	public EIATypeCaracteristicasSubTab(EIATypeTab tab) {
		setTitle("Caracteristicas");
		setPaneMargin(0);
		form = new EIATypeCaracteristicasFormPanel(tab);
		setPane(form);
	}

	@Override
	public void select(EiaType eiaType) {
		form.select(eiaType);
	}

	@Override
	public void close() {
		form.close();		
	}

}
