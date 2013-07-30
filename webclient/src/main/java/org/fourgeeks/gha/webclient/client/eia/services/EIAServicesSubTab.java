package org.fourgeeks.gha.webclient.client.eia.services;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIAServicesSubTab extends Tab implements EIATypeSelectionListener, GHAClosable {
	
	private EIAServicesGridPanel servicesGridPanel = new EIAServicesGridPanel();
	
	public EIAServicesSubTab() {
		setTitle("Servicios");
		setPaneMargin(0);
		
		setPane(servicesGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		servicesGridPanel.close();
	}
}
