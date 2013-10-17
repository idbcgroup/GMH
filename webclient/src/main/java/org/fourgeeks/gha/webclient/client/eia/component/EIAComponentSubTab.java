package org.fourgeeks.gha.webclient.client.eia.component;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

/**
 * @author alacret
 * 
 */
public class EIAComponentSubTab extends GHASubTab implements
		EIASelectionListener {

	private EIAComponentGridPanel eiaComponentGridPanel;

	/**
	 * @param tab
	 */
	public EIAComponentSubTab(EIATab tab) {
		super("Componentes", tab);
		setDisabled(true);
		tab.addEiaSelectionListener(this);
		eiaComponentGridPanel = new EIAComponentGridPanel();
		addGHAClosableHandler(eiaComponentGridPanel);
		addGHAHideableHandler(eiaComponentGridPanel);

		setPane(eiaComponentGridPanel);
	}

	@Override
	public void select(Eia eia) {
		eiaComponentGridPanel.select(eia);
		setDisabled(false);
	}
}