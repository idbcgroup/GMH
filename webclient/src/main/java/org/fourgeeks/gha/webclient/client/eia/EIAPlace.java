package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHAPlace;
import org.fourgeeks.gha.webclient.client.UI.GHATab;
import org.fourgeeks.gha.webclient.client.UI.GHATabSet;

import com.smartgwt.client.widgets.layout.VLayout;

public class EIAPlace implements GHAPlace {
	private GHATab tab;

	public EIAPlace() {
		tab = GHATabSet.getById(EIATab.ID);
		if (tab == null)
			tab = new EIATab();

		EIAGrid eiaGrid = new EIAGrid();
		VLayout verticalPanel = new VLayout();
		verticalPanel.addMember(eiaGrid);

		tab.setPane(verticalPanel);

	}

	@Override
	public void show() {
		GHATabSet.addTab(tab);
	}
}