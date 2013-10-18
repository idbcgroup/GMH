package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;

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
		Window.alert("1");
		tab = GHATabSet.getById(EIATab.ID);
		Window.alert("2");
		if (tab == null)
			tab = new EIATab(token);
		Window.alert("3");
	}

	@Override
	public void show() {
		Window.alert("4");
		try {
			Window.alert("5");
			GHATabSet.showTab(tab);
			Window.alert("6");
		} catch (UnavailableToHideException e) {
			Window.alert("7");
			History.back();
		}
	}
}