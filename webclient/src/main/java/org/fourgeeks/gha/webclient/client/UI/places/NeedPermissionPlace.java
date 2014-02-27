package org.fourgeeks.gha.webclient.client.UI.places;

import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;

/**
 * @author alacret
 * 
 */
public abstract class NeedPermissionPlace extends NeedLoginPlace {

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public NeedPermissionPlace(String token) throws LoginNeededException,
			PermissionsNeededException {
		super(token);
		if (!GHASessionData.hasAppPermission(token))
			throw new PermissionsNeededException(token);
	}
}