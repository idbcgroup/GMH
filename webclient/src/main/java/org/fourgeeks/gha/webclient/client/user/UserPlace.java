package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceHeader;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;

/**
 * @author alacret
 * 
 */
public class UserPlace extends NeedPermissionPlace {
	private final UserPanel tab = new UserPanel();

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public UserPlace(String token) throws LoginNeededException,
			PermissionsNeededException {
		super(token);
		header = new GHAPlaceHeader(this);
		addHideableListener(tab);
		addClosableListener(tab);
		addMember(tab.getHeader());
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

	@Override
	public void show() {
		super.show();
		tab.show();
		tab.getHeader().show();
	}
}