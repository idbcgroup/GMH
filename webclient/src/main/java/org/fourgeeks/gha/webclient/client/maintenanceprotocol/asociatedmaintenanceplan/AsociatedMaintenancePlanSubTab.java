package org.fourgeeks.gha.webclient.client.maintenanceprotocol.asociatedmaintenanceplan;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolTab;

/**
 * @author emiliot
 * 
 */
public class AsociatedMaintenancePlanSubTab extends GHASubTab implements
		MaintenanceProtocolSelectionListener {

	private AsociatedMaintenancePlanGridPanel panel;

	public AsociatedMaintenancePlanSubTab(MaintenanceProtocolTab tab) {
		super("Planes de Mant.", tab);
		setDisabled(true);

		// register as maintenance protocol listener with the tab
		tab.addMaintenanceProtocolSelectionListener(this);

		panel = new AsociatedMaintenancePlanGridPanel(this);
		addClosableHandler(panel);
		addHideableHandler(panel);

		setPane(panel);
	}

	// Consumer stuff
	@Override
	public void select(MaintenanceProtocol maintenanceProtocol) {
		panel.select(maintenanceProtocol);
		setDisabled(false);
	}

}