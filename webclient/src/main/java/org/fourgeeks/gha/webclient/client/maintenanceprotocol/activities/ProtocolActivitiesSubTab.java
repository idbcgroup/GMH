package org.fourgeeks.gha.webclient.client.maintenanceprotocol.activities;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolTab;

public class ProtocolActivitiesSubTab extends GHASubTab implements
		EIATypeSelectionListener{

	private ProtocolActivitiesGridPanel form;

	public ProtocolActivitiesSubTab(MaintenanceProtocolTab tab) {
		super("Actividades", tab);
		
		form = new ProtocolActivitiesGridPanel();
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);
		
		setPane(form);
	}

	@Override
	public void select(EiaType eiaType) {
//		form.select(eiaType);
	}
}
