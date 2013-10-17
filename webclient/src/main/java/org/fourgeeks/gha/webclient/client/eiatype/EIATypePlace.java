package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.tabs.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

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
			tab = new EIATypeTab(token);
	}

	@Override
	public void show() {
		GHATabSet.showTab(tab);
	}
}