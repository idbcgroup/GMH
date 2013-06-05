package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabSet;

public class EIATypePlace extends GHAPlace {
	private GHATab tab;

	public EIATypePlace() {
		tab = GHATabSet.getById(EIATypeTab.ID);
		if (tab == null)
			tab = new EIATypeTab();
	}

	@Override
	public void show() {
		GHATabSet.addTab(tab);
	}
}