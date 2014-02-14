package org.fourgeeks.gha.webclient.client.eia.movements;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIAPanel;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

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
	 * @param panel
	 */
	public EIAMovementsSubTab(EIAPanel panel) {
		super("Movimientos");// TODO Uistrings
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		setDisabled(true);
		panel.addEiaSelectionListener(this);
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