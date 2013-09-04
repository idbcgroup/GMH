package org.fourgeeks.gha.webclient.client.maintenanceactivity.activities;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityTab;

public class ActivitiesSubTab extends GHASubTab implements
		EIATypeSelectionListener{

	private ActivitiesGridPanel form;

	public ActivitiesSubTab(MaintenanceActivityTab tab) {
		super("Actividades", tab);
		
		form = new ActivitiesGridPanel();
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);
		
		setPane(form);
	}

	@Override
	public void select(EiaType eiaType) {
//		form.select(eiaType);
	}
}
