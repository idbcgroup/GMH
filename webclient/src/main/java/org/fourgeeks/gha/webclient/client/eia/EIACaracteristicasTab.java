package org.fourgeeks.gha.webclient.client.eia;

import com.smartgwt.client.widgets.tab.Tab;

public class EIACaracteristicasTab extends Tab {

	private EIAGridPanel eiaGridPanel = new EIAGridPanel();

	public EIACaracteristicasTab() {

		setTitle("Equipos");
		setPaneMargin(0);

		setPane(eiaGridPanel);
	}

	public EIAGridPanel getEiaGridPanel() {
		return eiaGridPanel;
	}
}
