package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.webclient.client.UI.tabs.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

/**
 * @author alacret
 * 
 */
public class MaintenanceActivityPlace extends GHAPlace {
	private GHATab tab;

	/**
	 * @param token
	 */
	public MaintenanceActivityPlace(String token) {
		super(token);
		tab = GHATabSet.getById(MaintenanceActivityTab.ID);
		if (tab == null)
			tab = new MaintenanceActivityTab(token);
	}

	@Override
	public void show() {
		GHATabSet.showTab(tab);
	}
}