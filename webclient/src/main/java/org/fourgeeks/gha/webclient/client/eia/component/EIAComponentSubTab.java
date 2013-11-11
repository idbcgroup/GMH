package org.fourgeeks.gha.webclient.client.eia.component;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

/**
 * @author alacret
 * 
 */
public class EIAComponentSubTab extends GHASubTab implements
		EIASelectionListener {

	private EIAComponentGridPanel componentGridPanel;

	/**
	 * @param tab
	 */
	public EIAComponentSubTab(EIATab tab) {
		super(GHAStrings.get("components"), tab);

		componentGridPanel = new EIAComponentGridPanel();
		addClosableListener(componentGridPanel);
		addHideableListener(componentGridPanel);

		setPane(componentGridPanel);

		tab.addEiaSelectionListener(this);
	}

	@Override
	public void select(Eia eia) {
		componentGridPanel.select(eia);
	}
}