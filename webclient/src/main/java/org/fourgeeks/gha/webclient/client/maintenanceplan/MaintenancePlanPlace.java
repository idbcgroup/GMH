package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;

/**
 * @author alacret
 * 
 */
public class MaintenancePlanPlace extends NeedPermissionPlace {
	private final GHATab tab = new MaintenancePlanTab();

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public MaintenancePlanPlace(String token) throws LoginNeededException,
			PermissionsNeededException {
		super(token);
		addMember(tab);
	}

	@Override
	public String getId() {
		return "mplan";
	}

	@Override
	public String getAcronym() {
		return GHAStrings.get("maintenance");
	}
}