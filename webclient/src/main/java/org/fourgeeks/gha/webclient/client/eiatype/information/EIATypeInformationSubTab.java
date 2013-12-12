package org.fourgeeks.gha.webclient.client.eiatype.information;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePanel;

/**
 * @author alacret
 * 
 */
public class EIATypeInformationSubTab extends GHASubTab {

	private final EIATypeInformationFormPanel form;

	/**
	 * @param tab
	 */
	public EIATypeInformationSubTab(EIATypePanel tab) {
		super(GHAStrings.get("information"), tab);

		form = new EIATypeInformationFormPanel();
		addClosableListener(form);
		addHideableListener(form);

		setPane(form);

		form.addEiaTypeSelectionListener(tab);
		tab.addEiaTypeSelectionListener(form);
	}

	public void show() {
		form.show();
	}
}
