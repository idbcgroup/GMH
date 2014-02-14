package org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityPanel;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceSubprotocolActivitiesSubTab extends GHASubTab implements
		MaintenanceActivitySubProtocolListener {

	private final MaintenanceSubprotocolActivitiesGridPanel maintenanceSubprotocolGridPanel;

	/**
	 * 
	 * @param panel
	 */
	public MaintenanceSubprotocolActivitiesSubTab(MaintenanceActivityPanel panel) {
		super(GHAStrings.get("subprotocol-activities"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		maintenanceSubprotocolGridPanel = new MaintenanceSubprotocolActivitiesGridPanel();
		setPane(maintenanceSubprotocolGridPanel);

		panel.addMaintenanceActivitySelectionListener(maintenanceSubprotocolGridPanel);
	}

	@Override
	public void changeSubTabState(boolean enable) {
		setDisabled(!enable);
	}
}