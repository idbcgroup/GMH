package org.fourgeeks.gha.webclient.client.eiatype.component;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

/**
 * @author alacret
 * 
 */
public class EIATypeComponentSubTab extends GHASubTab implements
		EIATypeSelectionListener {

	private EIATypeComponentGridPanel partesGridPanel;

	/**
	 * @param tab
	 */
	public EIATypeComponentSubTab(EIATypeTab tab) {
		super(GHAStrings.get("components"), tab);

		partesGridPanel = new EIATypeComponentGridPanel();
		addClosableHandler(partesGridPanel);
		addHideableHandler(partesGridPanel);

		setPane(partesGridPanel);

		tab.addEiaTypeSelectionListener(this);
	}

	@Override
	public void select(EiaType eiaType) {
		partesGridPanel.select(eiaType);
	}
}