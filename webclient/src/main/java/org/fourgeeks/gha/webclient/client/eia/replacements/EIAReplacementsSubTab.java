package org.fourgeeks.gha.webclient.client.eia.replacements;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIAReplacementsSubTab extends Tab implements EIATypeSelectionListener, GHAClosable{
	
	private EIAReplacementsGridPanel repuestosGridPanel = new EIAReplacementsGridPanel();
	
	public EIAReplacementsSubTab() {
		setTitle("Repuestos");
		setPaneMargin(0);
		
		setPane(repuestosGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		repuestosGridPanel.close();
	}
}