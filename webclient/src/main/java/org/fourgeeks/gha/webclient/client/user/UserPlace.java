package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;

/**
 * @author alacret
 * 
 */
public class UserPlace extends NeedPermissionPlace {
	private final GHATab tab = new UserTab();

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public UserPlace(String token) throws LoginNeededException,
			PermissionsNeededException {
		super(token);
		addMember(tab);
	}

	@Override
	public String getId() {
		return "user";
	}

	@Override
	public String getAcronym() {
		return GHAStrings.get("users");
	}
}