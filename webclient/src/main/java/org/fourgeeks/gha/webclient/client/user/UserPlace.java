package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.places.GHAPlaceSet;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;

import com.google.gwt.user.client.History;

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
	public void showPlace() {
		try {
			GHAPlaceSet.showPlace(this);
		} catch (UnavailableToHideException e) {
			History.back();
		}
	}

	@Override
	public String getId() {
		return "user";
	}
}