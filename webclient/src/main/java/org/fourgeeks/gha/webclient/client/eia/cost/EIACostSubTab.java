package org.fourgeeks.gha.webclient.client.eia.cost;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIAPanel;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

/**
 * @author alacret
 * 
 */
public class EIACostSubTab extends GHASubTab implements EIASelectionListener {

	private EIACostGridPanel eiaCostGridPanel;

	{
		eiaCostGridPanel = new EIACostGridPanel();
	}

	/**
	 * @param panel
	 */
	public EIACostSubTab(EIAPanel panel) {
		super("Costos y Depreciación");// TODO Uistrings
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		setDisabled(true);
		panel.addEiaSelectionListener(this);
		addClosableListener(eiaCostGridPanel);
		addHideableListener(eiaCostGridPanel);
		setPane(eiaCostGridPanel);
	}

	@Override
	public void select(Eia eia) {
		setDisabled(false);
	}
}
