package org.fourgeeks.gha.webclient.client.maintenanceprotocol.activities;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolTab;

/**
 * @author emiliot
 * 
 */
public class ProtocolActivitiesSubTab extends GHASubTab implements
		MaintenanceProtocolSelectionListener {

	private ProtocolActivitiesGridPanel panel;

	public ProtocolActivitiesSubTab(MaintenanceProtocolTab tab) {
		super("Actividades", tab);
		setDisabled(true);

		// register as maintenanceprotocol listener with the tab
		tab.addMaintenanceProtocolSelectionListener(this);

		panel = new ProtocolActivitiesGridPanel();
		addClosableListener(panel);
		addHideableListener(panel);

		setPane(panel);
	}

	// Consumer stuff
	@Override
	public void select(MaintenanceProtocol maintenanceProtocol) {
		panel.select(maintenanceProtocol);
		setDisabled(false);
	}
}
