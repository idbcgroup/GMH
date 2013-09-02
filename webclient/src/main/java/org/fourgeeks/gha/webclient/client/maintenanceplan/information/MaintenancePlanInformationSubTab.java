package org.fourgeeks.gha.webclient.client.maintenanceplan.information;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanTab;

public class MaintenancePlanInformationSubTab extends GHASubTab implements
		EIATypeSelectionListener{

	private MaintenancePlanInformationFormPanel form;

	public MaintenancePlanInformationSubTab(MaintenancePlanTab tab) {
		super("Información", tab);
		
		form = new MaintenancePlanInformationFormPanel(tab);
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);
		
		setPane(form);
	}

	@Override
	public void select(EiaType eiaType) {
		form.select(eiaType);
	}
}
