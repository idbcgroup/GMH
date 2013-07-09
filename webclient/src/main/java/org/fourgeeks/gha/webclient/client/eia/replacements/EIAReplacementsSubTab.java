package org.fourgeeks.gha.webclient.client.eia.replacements;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIAReplacementsSubTab extends Tab implements EIATypeSelectionListener{
	
	private EIAReplacementsGridPanel eiaRepuestosGridPanel = new EIAReplacementsGridPanel();
	
	public EIAReplacementsSubTab() {
		setTitle("Repuestos/Consumibles");
		setPaneMargin(0);
		
		setPane(eiaRepuestosGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
