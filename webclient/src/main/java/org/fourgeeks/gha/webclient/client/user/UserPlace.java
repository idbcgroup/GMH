package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATab;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATabSet;

public class UserPlace extends GHAPlace {
	private GHATab tab;

	public UserPlace() {
		tab = GHATabSet.getById(UserTab.ID);
		if (tab == null)
			tab = new UserTab();
	}

	@Override
	public void show() {
		tab.setToken(getToken());
		GHATabSet.showTab(tab);
	}
}