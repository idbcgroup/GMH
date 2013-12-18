package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocols;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPanel;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;

/**
 * @author emiliot
 * 
 */
public class MaintenanceProtocolsSubTab extends GHASubTab implements
		MaintenancePlanSelectionListener {

	private final MaintenanceProtocolsGridPanel maintenanceProtocolsGridPanel;

	/**
	 * @param tab
	 */
	public MaintenanceProtocolsSubTab(MaintenancePlanPanel tab) {
		super(GHAStrings.get("protocol"), tab);

		maintenanceProtocolsGridPanel = new MaintenanceProtocolsGridPanel();
		addClosableListener(maintenanceProtocolsGridPanel);
		addHideableListener(maintenanceProtocolsGridPanel);

		setPane(maintenanceProtocolsGridPanel);
		tab.addMaintenancePlanSelectionListener(this);
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		maintenanceProtocolsGridPanel.select(maintenancePlan);
	}

}