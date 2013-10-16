package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATab;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATabSet;

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
			tab = new MaintenancePlanTab();
	}

	@Override
	public void show() {
		tab.setToken(getToken());
		GHATabSet.showTab(tab);
	}
}