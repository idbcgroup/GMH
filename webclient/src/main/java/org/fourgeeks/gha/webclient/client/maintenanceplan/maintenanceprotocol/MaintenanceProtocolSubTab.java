package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPanel;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionProducer;

/**
 * @author emiliot
 * 
 */
public class MaintenanceProtocolSubTab extends GHASubTab implements
		MaintenanceProtocolSelectionProducer, MaintenancePlanSelectionListener {

	private final MaintenanceProtocolGridPanel maintenanceProtocolsGridPanel;

	/**
	 * @param panel
	 */
	public MaintenanceProtocolSubTab(MaintenancePlanPanel panel) {
		super(GHAStrings.get("protocol"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);

		maintenanceProtocolsGridPanel = new MaintenanceProtocolGridPanel();
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
			MaintenanceProtocolSelectionListener selectionListener) {
		maintenanceProtocolsGridPanel
				.addMaintenanceProtocolsSelectionListener(selectionListener);
	}

	@Override
	public void removeMaintenanceProtocolsSelectionListener(
			MaintenanceProtocolSelectionListener selectionListener) {
		maintenanceProtocolsGridPanel
				.removeMaintenanceProtocolsSelectionListener(selectionListener);
	}

	@Override
	public void notifyMaintenanceProtocols(MaintenanceProtocol entity) {
		maintenanceProtocolsGridPanel.notifyMaintenanceProtocols(entity);
	}

}