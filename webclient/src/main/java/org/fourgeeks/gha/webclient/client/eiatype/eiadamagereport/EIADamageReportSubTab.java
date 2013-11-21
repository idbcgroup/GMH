package org.fourgeeks.gha.webclient.client.eiatype.eiadamagereport;

import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionListener;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionProducer;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

public class EIADamageReportSubTab extends GHASubTab implements
		EiaDamageReportSelectionProducer {

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

	@Override
	public void addEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		damageReportPanel
				.addEiaDamageReportSelectionListener(eiaDamageReportSelectionListener);

	}

	@Override
	public void removeEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		damageReportPanel
				.removeEiaDamageReportSelectionListener(eiaDamageReportSelectionListener);
	}

	@Override
	public void notifyEiaDamageReport(EiaDamageReport eiaDamageReport) {
	}

}
