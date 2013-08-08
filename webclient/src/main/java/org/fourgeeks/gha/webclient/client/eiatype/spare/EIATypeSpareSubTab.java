package org.fourgeeks.gha.webclient.client.eiatype.spare;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

public class EIATypeSpareSubTab extends GHASubTab 
		implements EIATypeSelectionListener{
	
	private EIATypeSpareGridPanel eiaTypeRepuestosGridPanel;
	
	public EIATypeSpareSubTab(EIATypeTab tab) {
		super("Repuestos", tab);
		
		eiaTypeRepuestosGridPanel = new EIATypeSpareGridPanel();
		addGHAClosableHandler(eiaTypeRepuestosGridPanel);
		addGHAHideableHandler(eiaTypeRepuestosGridPanel);
		
		setPane(eiaTypeRepuestosGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		eiaTypeRepuestosGridPanel.select(eiaType);
	}
}
