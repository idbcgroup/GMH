package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

import com.google.gwt.user.client.History;

/**
 * @author alacret
 * 
 */
public class EIATypePlace extends NeedPermissionPlace {
	private GHATab tab;

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public EIATypePlace(String token) throws LoginNeededException,
			PermissionsNeededException {
		super(token);
		tab = GHATabSet.getById(EIATypeTab.ID);
		if (tab == null)
			tab = new EIATypeTab(token);
	}

	@Override
	public void show() {
		try {
			GHATabSet.showTab(tab);
		} catch (UnavailableToHideException e) {
			History.back();
		}
	}
}