package org.fourgeeks.gha.webclient.client.eia.cost;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

/**
 * @author alacret
 * 
 */
public class EIACostSubTab extends GHASubTab implements EIASelectionListener{

	private EIACostGridPanel eiaCostGridPanel;

	{
		eiaCostGridPanel = new EIACostGridPanel();
	}

	/**
	 * @param tab
	 */
	public EIACostSubTab(EIATab tab) {
		super("Costos y Depreciación", tab);
		setDisabled(true);
		tab.addEiaSelectionListener(this);
		addClosableListener(eiaCostGridPanel);
		addHideableListener(eiaCostGridPanel);
		setPane(eiaCostGridPanel);
	}

	@Override
	public void select(Eia eia) {
		setDisabled(false);
	}
}
