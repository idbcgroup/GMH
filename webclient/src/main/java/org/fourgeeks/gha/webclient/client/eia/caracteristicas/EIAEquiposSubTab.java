package org.fourgeeks.gha.webclient.client.eia.caracteristicas;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIAEquiposSubTab extends Tab implements EIATypeSelectionListener {
	
	private EIAGridPanel eiaGridPanel = new EIAGridPanel();	
	
	public EIAEquiposSubTab() {
		
		setTitle("Caracteristicas");
		setPaneMargin(0);
			
		setPane(eiaGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

}
