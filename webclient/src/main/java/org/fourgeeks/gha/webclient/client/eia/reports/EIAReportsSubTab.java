package org.fourgeeks.gha.webclient.client.eia.reports;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

public class EIAReportsSubTab extends GHASubTab implements EIASelectionListener {
	private EIAReportsFormPanel eiaReportsFormPanel;

	public EIAReportsSubTab(EIATab eiaTab) {
		super("Reportes", eiaTab);
		setDisabled(false);
		eiaTab.addEiaSelectionListener(this);

		eiaReportsFormPanel = new EIAReportsFormPanel();
		addGHAClosableHandler(eiaReportsFormPanel);
		setPane(eiaReportsFormPanel);
	}

	@Override
	public void select(Eia eia) {
		setDisabled(false);
		eiaReportsFormPanel.select(eia);
	}

}
