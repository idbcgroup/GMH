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
	 * @param tab
	 */
	public EIATypeUtilitySubTab(EIATypePanel tab) {
		super(GHAStrings.get("utility-services"), tab);

		eiaTypeServicesGridPanel = new EIATypeUtilityGridPanel();
		addClosableListener(eiaTypeServicesGridPanel);
		addHideableListener(eiaTypeServicesGridPanel);

		setPane(eiaTypeServicesGridPanel);

		tab.addEiaTypeSelectionListener(eiaTypeServicesGridPanel);
	}

}
