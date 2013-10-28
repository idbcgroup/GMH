package org.fourgeeks.gha.webclient.client.edt;

import org.fourgeeks.gha.webclient.client.UI.tabs.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabSet;

/**
 * @author alacret
 * 
 */
public class EDTPlace extends GHAPlace {
	private GHATab tab;

	/**
	 * @param token
	 */
	public EDTPlace(String token) {
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