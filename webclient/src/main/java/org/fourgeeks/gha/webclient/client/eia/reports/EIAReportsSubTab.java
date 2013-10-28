package org.fourgeeks.gha.webclient.client.eia.reports;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

/**
 * @author naramirez
 * 
 */
public class EIAReportsSubTab extends GHASubTab implements EIASelectionListener {
	private EIAReportsFormPanel eiaReportsFormPanel;

	public EIAReportsSubTab(EIATab eiaTab) {
		super(GHAStrings.get("reports"), eiaTab);

		eiaReportsFormPanel = new EIAReportsFormPanel();
		addGHAClosableHandler(eiaReportsFormPanel);
		addGHAHideableHandler(eiaReportsFormPanel);

		setPane(eiaReportsFormPanel);

		eiaTab.addEiaSelectionListener(this);
	}

	@Override
	public void select(Eia eia) {
		setDisabled(false);
	}
}
