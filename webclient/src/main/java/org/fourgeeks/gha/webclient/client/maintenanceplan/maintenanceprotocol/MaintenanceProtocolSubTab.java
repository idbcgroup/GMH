package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanTab;

public class MaintenanceProtocolSubTab extends GHASubTab implements
		EIATypeSelectionListener{

	private MaintenanceProtocolGridPanel form;

	public MaintenanceProtocolSubTab(MaintenancePlanTab tab) {
		super("Protocolos", tab);
		
		form = new MaintenanceProtocolGridPanel();
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);
		
		setPane(form);
	}

	@Override
	public void select(EiaType eiaType) {
//		form.select(eiaType);
	}
}
