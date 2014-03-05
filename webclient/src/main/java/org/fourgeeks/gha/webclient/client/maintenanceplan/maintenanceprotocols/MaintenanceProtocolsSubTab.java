package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocols;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPanel;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocols.MaintenanceProtocolsSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocols.MaintenanceProtocolsSelectionProducer;

/**
 * @author emiliot
 * 
 */
public class MaintenanceProtocolsSubTab extends GHASubTab implements
		MaintenanceProtocolsSelectionProducer, MaintenancePlanSelectionListener {

	private final MaintenanceProtocolsGridPanel maintenanceProtocolsGridPanel;

	/**
	 * @param panel
	 */
	public MaintenanceProtocolsSubTab(MaintenancePlanPanel panel) {
		super(GHAStrings.get("protocol"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);

		maintenanceProtocolsGridPanel = new MaintenanceProtocolsGridPanel();
		addClosableListener(maintenanceProtocolsGridPanel);
		addHideableListener(maintenanceProtocolsGridPanel);

		setPane(maintenanceProtocolsGridPanel);
		panel.addMaintenancePlanSelectionListener(this);
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		maintenanceProtocolsGridPanel.select(maintenancePlan);
	}

	@Override
	public void addMaintenanceProtocolsSelectionListener(
			MaintenanceProtocolsSelectionListener selectionListener) {
		maintenanceProtocolsGridPanel
				.addMaintenanceProtocolsSelectionListener(selectionListener);
	}

	@Override
	public void removeMaintenanceProtocolsSelectionListener(
			MaintenanceProtocolsSelectionListener selectionListener) {
		maintenanceProtocolsGridPanel
				.removeMaintenanceProtocolsSelectionListener(selectionListener);
	}

	@Override
	public void notifyMaintenanceProtocols(MaintenanceProtocol entity) {
		maintenanceProtocolsGridPanel.notifyMaintenanceProtocols(entity);
	}

}