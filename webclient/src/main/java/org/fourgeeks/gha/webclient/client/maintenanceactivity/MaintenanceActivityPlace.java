package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATab;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATabSet;

public class MaintenanceActivityPlace extends GHAPlace {
	private GHATab tab;

	public MaintenanceActivityPlace() {
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