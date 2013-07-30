package org.fourgeeks.gha.webclient.client.eia.component;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIAComponentSubTab extends Tab implements EIATypeSelectionListener, GHAClosable{
	
	private EIAComponentGridPanel eiaComponentGridPanel = new EIAComponentGridPanel();
	
	public EIAComponentSubTab() {
		setTitle("Componentes");
		setPaneMargin(0);
		
		setPane(eiaComponentGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		eiaComponentGridPanel.close();
	}
}
