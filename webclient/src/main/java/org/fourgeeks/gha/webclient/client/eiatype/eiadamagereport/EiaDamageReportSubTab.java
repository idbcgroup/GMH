package org.fourgeeks.gha.webclient.client.eiatype.eiadamagereport;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

public class EiaDamageReportSubTab extends GHASubTab {

	private EiaDamageReportGridPanel damageReportPanel;

	public EiaDamageReportSubTab(EIATypeTab tab) {
		super(GHAStrings.get("maintenance"), tab);

		damageReportPanel = new EiaDamageReportGridPanel();

		addClosableListener(damageReportPanel);
		addHideableListener(damageReportPanel);

		setPane(damageReportPanel);

		tab.addEiaTypeSelectionListener(damageReportPanel);
	}

}
