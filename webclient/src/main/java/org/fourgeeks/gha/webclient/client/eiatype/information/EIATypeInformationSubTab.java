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
	 * @param panel
	 */
	public EIATypeInformationSubTab(EIATypePanel panel) {
		super(GHAStrings.get("information"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		form = new EIATypeInformationFormPanel();
		addClosableListener(form);
		addHideableListener(form);

		setPane(form);

		form.addEiaTypeSelectionListener(panel);
		panel.addEiaTypeSelectionListener(form);
	}

	/**
	 * 
	 */
	public void show() {
		form.show();
	}
}
