package org.fourgeeks.gha.webclient.client.eia.movements;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

/**
 * @author alacret
 * 
 */
public class EIAMovementsSubTab extends GHASubTab implements
		EIASelectionListener {

	private EIAMovementsGridPanel eiaMovementsGridPanel;

	{
		eiaMovementsGridPanel = new EIAMovementsGridPanel();
	}

	/**
	 * @param tab
	 */
	public EIAMovementsSubTab(EIATab tab) {
		super("Movimientos", tab);
		tab.addEiaSelectionListener(this);
		addGHAClosableHandler(eiaMovementsGridPanel);
		addGHAHideableHandler(eiaMovementsGridPanel);
		setPane(eiaMovementsGridPanel);
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub

	}
}