package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

import com.google.gwt.user.client.History;

/**
 * @author alacret
 * 
 */
public class EIAPlace extends GHAPlace {
	private GHATab tab;

	/**
	 * @param token
	 * 
	 */
	public EIAPlace(String token) {
		super(token);
		tab = GHATabSet.getById(EIATab.ID);
		if (tab == null)
			tab = new EIATab(token);
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