package org.fourgeeks.gha.webclient.client.UI.places;

import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;

/**
 * @author alacret
 * 
 */
public abstract class NeedLoginPlace extends GHAPlace {

	/**
	 * @param token
	 * @throws LoginNeededException
	 */
	public NeedLoginPlace(String token) throws LoginNeededException {
		super(token);
		if (!GHASessionData.userisLogged())
			throw new LoginNeededException();
	}
}