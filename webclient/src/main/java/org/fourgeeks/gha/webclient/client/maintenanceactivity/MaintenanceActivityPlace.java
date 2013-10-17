package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATab;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATabSet;

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
			tab = new MaintenanceActivityTab();
	}

	@Override
	public void show() {
		tab.setToken(getToken());
		GHATabSet.showTab(tab);
	}
}