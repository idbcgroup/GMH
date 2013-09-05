package org.fourgeeks.gha.webclient.client.maintenanceactivity.information;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityTab;

public class MaintenanceActivityInformationSubTab extends GHASubTab implements
		EIATypeSelectionListener{

	private MaintenanceActivityInformationFormPanel form;

	public MaintenanceActivityInformationSubTab(MaintenanceActivityTab tab) {
		super("Información", tab);
		
		form = new MaintenanceActivityInformationFormPanel(tab);
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);
		
		setPane(form);
	}

	@Override
	public void select(EiaType eiaType) {
		form.select(eiaType);
	}
}
