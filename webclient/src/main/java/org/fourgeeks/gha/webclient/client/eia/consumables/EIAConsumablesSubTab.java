package org.fourgeeks.gha.webclient.client.eia.consumables;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIAConsumablesSubTab extends Tab implements EIATypeSelectionListener, GHAClosable{
	
	private EIAConsumablesGridPanel consumablesGridPanel = new EIAConsumablesGridPanel();
	
	public EIAConsumablesSubTab() {
		setTitle("Consumibles");
		setPaneMargin(0);
		
		setPane(consumablesGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		consumablesGridPanel.close();
	}
}
