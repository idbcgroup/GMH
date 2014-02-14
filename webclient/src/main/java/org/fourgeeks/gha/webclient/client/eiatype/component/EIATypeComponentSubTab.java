package org.fourgeeks.gha.webclient.client.eiatype.component;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePanel;

/**
 * @author alacret
 * 
 */
public class EIATypeComponentSubTab extends GHASubTab {

	private EIATypeComponentGridPanel partesGridPanel;

	/**
	 * @param panel
	 */
	public EIATypeComponentSubTab(EIATypePanel panel) {
		super(GHAStrings.get("components"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);

		partesGridPanel = new EIATypeComponentGridPanel();
		addClosableListener(partesGridPanel);
		addHideableListener(partesGridPanel);

		setPane(partesGridPanel);

		panel.addEiaTypeSelectionListener(partesGridPanel);
	}
}