package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

/**
 * @author alacret
 * 
 */
public class MaintenancePlanPlace extends NeedPermissionPlace {
	private GHATab tab;

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public MaintenancePlanPlace(String token) throws LoginNeededException,
			PermissionsNeededException {
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