package org.fourgeeks.gha.webclient.client.eia.movements;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class EIAMovementsSubTab extends Tab implements EIATypeSelectionListener{
	
	public EIAMovementsSubTab() {
		setTitle("Movimientos");
		setPaneMargin(0);
		
		setPane(new VLayout());
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
