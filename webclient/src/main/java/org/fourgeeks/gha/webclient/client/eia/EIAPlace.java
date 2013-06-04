package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabSet;

public class EIAPlace implements GHAPlace {
	private GHATab tab;

	public EIAPlace(String token) {
		tab = GHATabSet.getById(EIATab.ID);
		if (tab == null)
			tab = new EIATab();
		((EIATab) tab).setToken(token);
	}

	@Override
	public void show() {
		GHATabSet.addTab(tab);
	}
}