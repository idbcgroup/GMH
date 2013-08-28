package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.webclient.client.UI.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabSet;

public class MaintenancePlanPlace extends GHAPlace {
	private GHATab tab;

	public MaintenancePlanPlace() {
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