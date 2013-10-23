package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import org.fourgeeks.gha.webclient.client.UI.tabs.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

/**
 * @author alacret
 * 
 */
public class MaintenanceProtocolPlace extends GHAPlace {
	private GHATab tab;

	/**
	 * @param token
	 */
	public MaintenanceProtocolPlace(String token) {
		super(token);
		tab = GHATabSet.getById(MaintenanceProtocolTab.ID);
		if (tab == null)
			tab = new MaintenanceProtocolTab(token);
	}

	@Override
	public void show() {
		GHATabSet.showTab(tab);
	}
}