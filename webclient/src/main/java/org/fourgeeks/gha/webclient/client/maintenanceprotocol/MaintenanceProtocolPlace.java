package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;

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
	}

	@Override
	public void showPlace() {
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
}