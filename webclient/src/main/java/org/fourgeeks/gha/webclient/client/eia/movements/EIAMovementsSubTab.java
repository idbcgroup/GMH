package org.fourgeeks.gha.webclient.client.eia.movements;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIAMovementsSubTab extends Tab implements EIATypeSelectionListener, GHAClosable{
	
	private EIAMovementsGridPanel movementsGridPanel = new EIAMovementsGridPanel();
	
	public EIAMovementsSubTab() {
		setTitle("Movimientos");
		setPaneMargin(0);
		
		setPane(movementsGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		movementsGridPanel.close();
	}
}
