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
	private EIAReportsFormPanel form;

	public EIAReportsSubTab(EIATab eiaTab) {
		super(GHAStrings.get("reports"), eiaTab);

		form = new EIAReportsFormPanel();
		addClosableListener(form);
		addHideableListener(form);

		setPane(form);

		eiaTab.addEiaSelectionListener(this);
	}

	@Override
	public void select(Eia eia) {
		setDisabled(false);
		form.show();
	}

	public void showSelectedSection() {
		form.showSelectedSection();

	}
}
