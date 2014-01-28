package org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocolactivities;

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
	 * @param tab
	 */
	public MaintenanceSubprotocolActivitiesSubTab(MaintenanceActivityPanel tab) {
		super("Sub-protocolos actividades", tab);
		maintenanceSubprotocolGridPanel = new MaintenanceSubprotocolActivitiesGridPanel();
		setPane(maintenanceSubprotocolGridPanel);
	}

	@Override
	public void changeSubTabState(boolean enable) {
		setDisabled(!enable);
	}
}