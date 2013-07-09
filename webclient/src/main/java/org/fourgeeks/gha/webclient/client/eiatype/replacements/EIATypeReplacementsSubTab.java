package org.fourgeeks.gha.webclient.client.eiatype.replacements;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIATypeReplacementsSubTab extends Tab 
		implements EIATypeSelectionListener, GHAClosable{
	
	private EIATypeReplacementsGridPanel eiaTypeRepuestosGridPanel = new EIATypeReplacementsGridPanel();
	
	public EIATypeReplacementsSubTab() {
		setTitle("Repuestos/Consumibles");
		setPaneMargin(0);
		
		setPane(eiaTypeRepuestosGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}
