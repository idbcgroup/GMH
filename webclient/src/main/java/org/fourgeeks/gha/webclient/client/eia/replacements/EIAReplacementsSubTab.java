package org.fourgeeks.gha.webclient.client.eia.replacements;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIATab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

public class EIAReplacementsSubTab extends GHASubTab implements EIATypeSelectionListener{
	
	private EIAReplacementsGridPanel eiaRepuestosGridPanel = null;
	
	public EIAReplacementsSubTab(EIATab tab) {
		super("Repuestos", tab);
		
		eiaRepuestosGridPanel = new EIAReplacementsGridPanel();
		addGHAClosableHandler(eiaRepuestosGridPanel);
		addGHAHideableHandler(eiaRepuestosGridPanel);
		
		setPane(eiaRepuestosGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
