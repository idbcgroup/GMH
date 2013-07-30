package org.fourgeeks.gha.webclient.client.eiatype.consumables;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIATypeConsumablesSubTab extends Tab 
		implements EIATypeSelectionListener, GHAClosable{
	
	private EIATypeConsumablesGridPanel eiaTypeConsumablesGridPanel = new EIATypeConsumablesGridPanel();
	
	public EIATypeConsumablesSubTab() {
		setTitle("Consumibles");
		setPaneMargin(0);
		
		setPane(eiaTypeConsumablesGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		eiaTypeConsumablesGridPanel.close();
	}
}
