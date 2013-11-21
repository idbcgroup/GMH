package org.fourgeeks.gha.webclient.client.eiatype.eiadamagereport;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

public class EIADamageReportSubTab extends GHASubTab {

	private EiaDamageReportGridPanel damageReportPanel;

	public EIADamageReportSubTab(EIATypeTab tab) {
		super(GHAStrings.get("eiaDamageReport"), tab);

		damageReportPanel = new EiaDamageReportGridPanel();

		addClosableListener(damageReportPanel);
		addHideableListener(damageReportPanel);

		setPane(damageReportPanel);

		tab.addEiaTypeSelectionListener(damageReportPanel);
	}

	public void show() {
		damageReportPanel.show();
	}

}
