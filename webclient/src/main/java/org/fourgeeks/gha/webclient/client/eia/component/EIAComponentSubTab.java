package org.fourgeeks.gha.webclient.client.eia.component;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIAComponentSubTab extends Tab implements EIATypeSelectionListener{
	
	private EIAComponentGridPanel eiaPartesGridPanel = new EIAComponentGridPanel();
	
	public EIAComponentSubTab() {
		setTitle("Partes/Componentes");
		setPaneMargin(0);
		
		setPane(eiaPartesGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
