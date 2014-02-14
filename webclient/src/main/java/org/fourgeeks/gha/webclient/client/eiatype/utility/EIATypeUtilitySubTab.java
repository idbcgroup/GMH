package org.fourgeeks.gha.webclient.client.eiatype.utility;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePanel;

/**
 * @author alacret
 * 
 */
public class EIATypeUtilitySubTab extends GHASubTab {

	private EIATypeUtilityGridPanel eiaTypeServicesGridPanel;

	/**
	 * @param panel
	 */
	public EIATypeUtilitySubTab(EIATypePanel panel) {
		super(GHAStrings.get("utility-services"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		eiaTypeServicesGridPanel = new EIATypeUtilityGridPanel();
		addClosableListener(eiaTypeServicesGridPanel);
		addHideableListener(eiaTypeServicesGridPanel);

		setPane(eiaTypeServicesGridPanel);

		panel.addEiaTypeSelectionListener(eiaTypeServicesGridPanel);
	}

}
