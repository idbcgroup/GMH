package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

/**
 * @author alacret
 * 
 */
@Deprecated
public class MaintenanceProtocolPlace extends NeedPermissionPlace {
	private GHATab tab;

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public MaintenanceProtocolPlace(String token) throws LoginNeededException,
			PermissionsNeededException {
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