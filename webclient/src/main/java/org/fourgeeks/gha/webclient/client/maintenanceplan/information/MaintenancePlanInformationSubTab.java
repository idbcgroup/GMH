package org.fourgeeks.gha.webclient.client.maintenanceplan.information;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanTab;

public class MaintenancePlanInformationSubTab extends GHASubTab implements
		MaintenancePlanSelectionListener {

	private final MaintenancePlanInformationFormPanel form;

	public MaintenancePlanInformationSubTab(MaintenancePlanTab tab) {
		super(GHAStrings.get("information"), tab);

		form = new MaintenancePlanInformationFormPanel();
		addClosableListener(form);
		addHideableListener(form);

		setPane(form);

		// register to listen for selected maintenance plan
		tab.addMaintenancePlanSelectionListener(this);
		form.addMaintenancePlanSelectionListener(tab);
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		form.select(maintenancePlan);
	}

	public void show() {
		form.show();
	}
}
