package org.fourgeeks.gha.webclient.client.edt;

import org.fourgeeks.gha.webclient.client.UI.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabSet;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

public class EDTPlace extends GHAPlace {
	private GHATab tab;

	public EDTPlace() {
		tab = GHATabSet.getById(EIATab.ID);
		if (tab == null)
			tab = new EDTTab();
	}

	@Override
	public void show() {
		tab.setToken(getToken());
		GHATabSet.showTab(tab);
	}
}