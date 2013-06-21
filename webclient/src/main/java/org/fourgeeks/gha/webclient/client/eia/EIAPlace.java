package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabSet;

public class EIAPlace extends GHAPlace {
	private GHATab tab;

	public EIAPlace() {
		tab = GHATabSet.getById(EIATab.ID);
		if (tab == null)
			tab = new EIATab(null);
	}

	@Override
	public void show() {
		tab.setToken(getToken());
		GHATabSet.addTab(tab);
	}
}