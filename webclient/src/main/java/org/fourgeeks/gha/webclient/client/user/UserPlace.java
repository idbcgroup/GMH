package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.webclient.client.UI.tabs.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

/**
 * @author alacret
 * 
 */
public class UserPlace extends GHAPlace {
	private GHATab tab;

	/**
	 * @param token
	 */
	public UserPlace(String token) {
		super(token);
		tab = GHATabSet.getById(UserTab.ID);
		if (tab == null)
			tab = new UserTab(token);
	}

	@Override
	public void show() {
		GHATabSet.showTab(tab);
	}
}