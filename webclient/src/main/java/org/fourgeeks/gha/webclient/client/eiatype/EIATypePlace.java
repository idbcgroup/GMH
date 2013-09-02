package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATab;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATabSet;

public class EIATypePlace extends GHAPlace {
	private GHATab tab;

	public EIATypePlace() {
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