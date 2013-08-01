package org.fourgeeks.gha.webclient.client.eia.services;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIATab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

public class EIAServicesSubTab extends GHASubTab implements EIATypeSelectionListener {
	
	private EIAServicesGridPanel eiaServicesGridPanel = null;
	
	public EIAServicesSubTab(EIATab tab) {
		super("Servicios", tab);
		
		eiaServicesGridPanel = new EIAServicesGridPanel();
		addGHAClosableHandler(eiaServicesGridPanel);
		addGHAHideableHandler(eiaServicesGridPanel);
		
		setPane(eiaServicesGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
