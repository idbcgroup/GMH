package org.fourgeeks.gha.webclient.client.eiatype.component;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

/**
 * @author alacret
 * 
 */
public class EIATypeComponentSubTab extends GHASubTab {

	private EIATypeComponentGridPanel partesGridPanel;

	/**
	 * @param tab
	 */
	public EIATypeComponentSubTab(EIATypeTab tab) {
		super(GHAStrings.get("components"), tab);

		partesGridPanel = new EIATypeComponentGridPanel();
		addClosableListener(partesGridPanel);
		addHideableListener(partesGridPanel);

		setPane(partesGridPanel);

		tab.addEiaTypeSelectionListener(partesGridPanel);
	}
}