package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanTab;

/**
 * @author emiliot
 *
 */
public class MaintenanceProtocolSubTab extends GHASubTab implements
		MaintenancePlanSelectionListener{

	private MaintenanceProtocolGridPanel panel;

	public MaintenanceProtocolSubTab(MaintenancePlanTab tab) {
		super("Protocolos", tab);
		setDisabled(true);
		
		// register as maintenanceplan listener with the tab
		tab.addMaintenancePlanSelectionListener(this);
		
		panel = new MaintenanceProtocolGridPanel();
		addGHAClosableHandler(panel);
		addGHAHideableHandler(panel);
		
		setPane(panel);
	}

	//Consumer stuff
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener#select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		panel.select(maintenancePlan);
		setDisabled(false);
	}

}
