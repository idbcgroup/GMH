package org.fourgeeks.gha.webclient.client.eiatype.information;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

import com.smartgwt.client.widgets.tab.Tab;

public class EIATypeInformationSubTab extends Tab implements
		EIATypeSelectionListener, GHAClosable {

	private EIATypeInformationFormPanel form;

	public EIATypeInformationSubTab(EIATypeTab tab) {
		setTitle("Caracteristicas");
		setPaneMargin(0);
		form = new EIATypeInformationFormPanel(tab);
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
