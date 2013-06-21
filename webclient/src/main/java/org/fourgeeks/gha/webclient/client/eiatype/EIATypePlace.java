package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabSet;

public class EIATypePlace extends GHAPlace {
	public EIATypePlace() {
	}

	@Override
	public void show() {
		GHATab tab = GHATabSet.getById(EIATypeTab.ID);
		if (tab == null) {
			tab = new EIATypeTab(null);
			tab.setToken(getToken());
			GHATabSet.addTab(tab);
		} else {
			tab.setToken(getToken());
			GHATabSet.showTab(tab);
		}
	}
}