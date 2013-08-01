package org.fourgeeks.gha.webclient.client.eiatype.information;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

public class EIATypeInformationSubTab extends GHASubTab implements
		EIATypeSelectionListener{

	private EIATypeInformationFormPanel form;

	public EIATypeInformationSubTab(EIATypeTab tab) {
		super("Información", tab);
		
		form = new EIATypeInformationFormPanel(tab);
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);
		
		setPane(form);
	}

	@Override
	public void select(EiaType eiaType) {
		form.select(eiaType);
	}
}
