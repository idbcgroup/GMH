package org.fourgeeks.gha.webclient.client.eia.cost;

import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

/**
 * @author alacret
 * 
 */
public class EIACostSubTab extends GHASubTab {

	private EIACostGridPanel eiaCostGridPanel;

	{
		eiaCostGridPanel = new EIACostGridPanel();
	}

	/**
	 * @param tab
	 */
	public EIACostSubTab(EIATab tab) {
		super("Costos y Depreciación", tab);
		addGHAClosableHandler(eiaCostGridPanel);
		addGHAHideableHandler(eiaCostGridPanel);
		setPane(eiaCostGridPanel);
	}
}
