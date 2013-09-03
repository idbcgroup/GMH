package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATab;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATabSet;

public class MaintenanceProtocolPlace extends GHAPlace {
	private GHATab tab;

	public MaintenanceProtocolPlace() {
		tab = GHATabSet.getById(MaintenanceProtocolTab.ID);
		if (tab == null)
			tab = new MaintenanceProtocolTab();
	}

	@Override
	public void show() {
		tab.setToken(getToken());
		GHATabSet.showTab(tab);
	}
}