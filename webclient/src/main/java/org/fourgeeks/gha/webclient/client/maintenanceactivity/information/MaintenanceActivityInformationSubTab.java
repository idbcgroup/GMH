package org.fourgeeks.gha.webclient.client.maintenanceactivity.information;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityTab;

public class MaintenanceActivityInformationSubTab extends GHASubTab implements
		MaintenanceActivitySelectionListener {

	private MaintenanceActivityInformationFormPanel form;

	public MaintenanceActivityInformationSubTab(MaintenanceActivityTab tab) {
		super("Información", tab);

		form = new MaintenanceActivityInformationFormPanel(tab);
		addGHAClosableHandler(form);
		addGHAHideableHandler(form);

		setPane(form);

		// register to listen for a selected maintenanceactivity
		tab.addMaintenanceActivitySelectionListener(this);
		form.addMaintenanceActivitySelectionListener(tab);
	}

	// Consumer stuff
	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		form.setMaintenanceActivity(maintenanceActivity);
		setDisabled(false);
	}
}
