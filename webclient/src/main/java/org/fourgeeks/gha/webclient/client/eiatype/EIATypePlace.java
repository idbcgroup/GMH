package org.fourgeeks.gha.webclient.client.eiatype;

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
public class EIATypePlace extends NeedPermissionPlace {
	private final GHATab tab = new EIATypeTab();

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public EIATypePlace(String token) throws LoginNeededException,
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
		return "eiatype";
	}
}