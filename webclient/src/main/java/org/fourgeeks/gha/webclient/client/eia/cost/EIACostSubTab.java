package org.fourgeeks.gha.webclient.client.eia.cost;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIACostSubTab extends Tab implements EIATypeSelectionListener, GHAClosable {
	
	private EIACostGridPanel costGridPanel = new EIACostGridPanel();
	
	public EIACostSubTab() {
		setTitle("Costos y Depreciación");
		setPaneMargin(0);
		
		setPane(costGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		costGridPanel.close();
	}
}
