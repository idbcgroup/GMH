package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAPanel;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;

/**
 * @author alacret
 * 
 */
@Deprecated
public class MaintenanceActivityPlaceDeprecated extends NeedPermissionPlace {
	private GHAPanel tab;

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public MaintenanceActivityPlaceDeprecated(String token) throws LoginNeededException,
			PermissionsNeededException {
		super(token);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAcronym() {
		// TODO Auto-generated method stub
		return null;
	}
}