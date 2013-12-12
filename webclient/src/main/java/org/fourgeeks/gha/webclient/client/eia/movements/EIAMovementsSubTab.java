package org.fourgeeks.gha.webclient.client.eia.movements;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIAPanel;

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
	public EIAMovementsSubTab(EIAPanel tab) {
		super("Movimientos", tab);
		setDisabled(true);
		tab.addEiaSelectionListener(this);
		addClosableListener(eiaMovementsGridPanel);
		addHideableListener(eiaMovementsGridPanel);
		setPane(eiaMovementsGridPanel);
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub
		setDisabled(false);
	}
}