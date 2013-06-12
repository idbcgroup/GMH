package org.fourgeeks.gha.webclient.client.eia.repuestos;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class EIARepuestosSubTab extends Tab implements EIATypeSelectionListener{
	
	public EIARepuestosSubTab() {
		
		setTitle("Repuestos/Consumibles");
		setPaneMargin(0);
		
		setPane(new VLayout());
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}