package org.fourgeeks.gha.webclient.client.maintenanceplan.information;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanTab;

public class MaintenancePlanInformationSubTab extends GHASubTab implements MaintenancePlanSelectionListener{

	private MaintenancePlanInformationFormPanel form;

	public MaintenancePlanInformationSubTab(MaintenancePlanTab tab) {
		super("Información", tab);
		
		form = new MaintenancePlanInformationFormPanel(tab);
		addClosableHandler(form);
		addHideableHandler(form);
		
		setPane(form);
		
		//register to listen for selected maintenance plan
		tab.addMaintenancePlanSelectionListener(this);
		form.addMaintenancePlanSelectionListener(tab);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener#select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		setDisabled(false);
		form.setMaintenancePlan(maintenancePlan);
	}
}
