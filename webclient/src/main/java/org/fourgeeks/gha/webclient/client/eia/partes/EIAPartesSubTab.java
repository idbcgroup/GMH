package org.fourgeeks.gha.webclient.client.eia.partes;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIAPartesSubTab extends Tab implements EIATypeSelectionListener{
	
	private EIAPartesGridPanel eiaPartesGridPanel = new EIAPartesGridPanel();
	
	public EIAPartesSubTab() {
		setTitle("Partes/Componentes");
		setPaneMargin(0);
		
		setPane(eiaPartesGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
