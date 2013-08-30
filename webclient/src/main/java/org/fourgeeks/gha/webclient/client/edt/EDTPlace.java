package org.fourgeeks.gha.webclient.client.edt;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATab;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATabSet;

public class EDTPlace extends GHAPlace {
	private GHATab tab;

	public EDTPlace() {
		tab = GHATabSet.getById(EDTTab.ID);
		if (tab == null)
			tab = new EDTTab();
	}

	@Override
	public void show() {
		tab.setToken(getToken());
		GHATabSet.showTab(tab);
	}
}