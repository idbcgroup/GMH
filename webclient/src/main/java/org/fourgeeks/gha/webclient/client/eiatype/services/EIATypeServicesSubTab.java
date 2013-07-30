package org.fourgeeks.gha.webclient.client.eiatype.services;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIATypeServicesSubTab extends Tab 
		implements EIATypeSelectionListener, GHAClosable{
	
	private EIATypeServicesGridPanel eiaTypeServicesGridPanel = new EIATypeServicesGridPanel();
	
	public EIATypeServicesSubTab() {
		setTitle("Servicios");
		setPaneMargin(0);
		
		setPane(eiaTypeServicesGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		eiaTypeServicesGridPanel.close();
	}
}
