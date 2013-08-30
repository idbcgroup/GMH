package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATab;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATabSet;

public class EIAPlace extends GHAPlace {
	private GHATab tab;

	public EIAPlace() {
		tab = GHATabSet.getById(EIATab.ID);
		if (tab == null)
			tab = new EIATab();
	}

	@Override
	public void show() {
		tab.setToken(getToken());
		GHATabSet.showTab(tab);
	}
}