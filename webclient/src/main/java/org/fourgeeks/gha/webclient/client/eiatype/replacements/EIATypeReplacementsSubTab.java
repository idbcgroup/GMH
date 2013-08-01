package org.fourgeeks.gha.webclient.client.eiatype.replacements;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

public class EIATypeReplacementsSubTab extends GHASubTab 
		implements EIATypeSelectionListener{
	
	private EIATypeReplacementsGridPanel eiaTypeRepuestosGridPanel;
	
	public EIATypeReplacementsSubTab(EIATypeTab tab) {
		super("Repuestos", tab);
		
		eiaTypeRepuestosGridPanel = new EIATypeReplacementsGridPanel();
		addGHAClosableHandler(eiaTypeRepuestosGridPanel);
		addGHAHideableHandler(eiaTypeRepuestosGridPanel);
		
		setPane(eiaTypeRepuestosGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
