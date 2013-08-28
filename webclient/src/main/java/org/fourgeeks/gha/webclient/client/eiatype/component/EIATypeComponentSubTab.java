package org.fourgeeks.gha.webclient.client.eiatype.component;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
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
		super("Componentes", tab);
		tab.addEiaTypeSelectionListener(this);
		partesGridPanel = new EIATypeComponentGridPanel();
		addGHAClosableHandler(partesGridPanel);
		addGHAHideableHandler(partesGridPanel);

		setPane(partesGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		partesGridPanel.select(eiaType);
	}
}
