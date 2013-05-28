package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabSet;

public class EIAPlace implements GHAPlace {
	public static final String ID = "eia-tab";
	private GHATab tab;

	public EIAPlace() {
		tab = GHATabSet.getById(ID);
		if (tab == null)
			tab = new EIATab();
	}

	@Override
	public void show() {
		GHATabSet.addTab(ID, tab);
	}
}