package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATab;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATabSet;

/**
 * @author alacret
 * 
 */
public class EIATypePlace extends GHAPlace {
	private GHATab tab;

	/**
	 * @param token
	 * 
	 */
	public EIATypePlace(String token) {
		super(token);
		tab = GHATabSet.getById(EIATypeTab.ID);
		if (tab == null)
			tab = new EIATypeTab(null);
	}

	@Override
	public void show() {
		tab.setToken(getToken());
		GHATabSet.showTab(tab);
	}
}