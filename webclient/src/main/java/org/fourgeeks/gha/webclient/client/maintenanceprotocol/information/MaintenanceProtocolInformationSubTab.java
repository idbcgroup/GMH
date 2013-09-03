package org.fourgeeks.gha.webclient.client.maintenanceprotocol.information;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolTab;

public class MaintenanceProtocolInformationSubTab extends GHASubTab implements
		EIATypeSelectionListener{

	private MaintenanceProtocolInformationFormPanel form;

	public MaintenanceProtocolInformationSubTab(MaintenanceProtocolTab tab) {
		super("Información", tab);
		
		form = new MaintenanceProtocolInformationFormPanel(tab);
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);
		
		setPane(form);
	}

	@Override
	public void select(EiaType eiaType) {
		form.select(eiaType);
	}
}
