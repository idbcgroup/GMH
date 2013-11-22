package org.fourgeeks.gha.webclient.client.maintenanceactivity;

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
public class MaintenanceActivityPlace extends NeedPermissionPlace {
	private GHATab tab;

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public MaintenanceActivityPlace(String token) throws LoginNeededException,
			PermissionsNeededException {
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