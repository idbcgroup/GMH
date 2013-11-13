package org.fourgeeks.gha.webclient.client.edt;

import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;
import org.fourgeeks.gha.webclient.client.UI.exceptions.PermissionsNeededException;
import org.fourgeeks.gha.webclient.client.UI.places.NeedPermissionPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

/**
 * @author alacret
 * 
 */
public class EDTPlace extends NeedPermissionPlace {
	private GHATab tab;

	/**
	 * @param token
	 * @throws LoginNeededException
	 * @throws PermissionsNeededException
	 */
	public EDTPlace(String token) throws LoginNeededException,
			PermissionsNeededException {
		super(token);
		tab = GHATabSet.getById(EDTTab.ID);
		if (tab == null)
			tab = new EDTTab(token);
	}

	@Override
	public void show() {
		GHATabSet.showTab(tab);
	}
}