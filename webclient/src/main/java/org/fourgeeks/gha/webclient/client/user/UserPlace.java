package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

import com.google.gwt.user.client.History;

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

	public void show() {
		try {
			GHATabSet.showTab(tab);
		} catch (UnavailableToHideException e) {
			History.back();
		}
	}
}