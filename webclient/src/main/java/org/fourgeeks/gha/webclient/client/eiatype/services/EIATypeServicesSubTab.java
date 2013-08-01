package org.fourgeeks.gha.webclient.client.eiatype.services;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

public class EIATypeServicesSubTab extends GHASubTab 
		implements EIATypeSelectionListener, GHAClosable{
	
	private EIATypeServicesGridPanel eiaTypeServicesGridPanel;
	
	public EIATypeServicesSubTab(EIATypeTab tab) {
		super("Servicios",tab);
		
		eiaTypeServicesGridPanel = new EIATypeServicesGridPanel();
		addGHAClosableHandler(eiaTypeServicesGridPanel);
		addGHAHideableHandler(eiaTypeServicesGridPanel);
		
		setPane(eiaTypeServicesGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
