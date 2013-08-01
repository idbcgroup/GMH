package org.fourgeeks.gha.webclient.client.eia.movements;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIATab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

public class EIAMovementsSubTab extends GHASubTab implements EIATypeSelectionListener{
	
	private EIAMovementsGridPanel eiaMovementsGridPanel = null;
	
	public EIAMovementsSubTab(EIATab tab) {
		super("Movimientos", tab);
		
		eiaMovementsGridPanel = new EIAMovementsGridPanel();
		addGHAClosableHandler(eiaMovementsGridPanel);
		addGHAHideableHandler(eiaMovementsGridPanel);
		
		setPane(eiaMovementsGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
