package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.webclient.client.UI.tabs.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

/**
 * @author alacret
 * 
 */
public class MaintenancePlanPlace extends GHAPlace {
	private GHATab tab;

	/**
	 * @param token
	 */
	public MaintenancePlanPlace(String token) {
		super(token);
		tab = GHATabSet.getById(MaintenancePlanTab.ID);
		if (tab == null)
			tab = new MaintenancePlanTab(token);
	}

	@Override
	public void show() {
		GHATabSet.showTab(tab);
	}
}