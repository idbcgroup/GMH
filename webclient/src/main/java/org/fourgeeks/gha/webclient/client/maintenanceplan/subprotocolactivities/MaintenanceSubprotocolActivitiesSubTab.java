package org.fourgeeks.gha.webclient.client.maintenanceplan.subprotocolactivities;

import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPanel;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceSubprotocolActivitiesSubTab extends GHASubTab {

	private final MaintenanceSubprotocolActivitiesGridPanel maintenanceSubprotocolGridPanel;

	/**
	 * 
	 * @param tab
	 */
	public MaintenanceSubprotocolActivitiesSubTab(MaintenancePlanPanel tab) {
		super("Sub-protocolos actividades", tab);
		maintenanceSubprotocolGridPanel = new MaintenanceSubprotocolActivitiesGridPanel();
		setPane(maintenanceSubprotocolGridPanel);
	}
}