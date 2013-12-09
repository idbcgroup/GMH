package org.fourgeeks.gha.webclient.client.eia;

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
public class EIAPlace extends NeedPermissionPlace {
	private final GHATab tab = new EIATab();

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 * 
	 */
	public EIAPlace(String token) throws LoginNeededException,
			PermissionsNeededException {
		super(token);
		addMember(tab);
	}

	@Override
	public void show() {
		try {
			GHAPlaceSet.showPlace(this);
		} catch (UnavailableToHideException e) {
			History.back();
		}
	}

	@Override
	public String getId() {
		return "eia";
	}
}