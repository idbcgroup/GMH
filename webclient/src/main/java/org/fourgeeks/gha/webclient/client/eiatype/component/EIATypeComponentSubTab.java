package org.fourgeeks.gha.webclient.client.eiatype.component;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

public class EIATypeComponentSubTab extends GHASubTab implements EIATypeSelectionListener{
	
	private EIATypeComponentGridPanel partesGridPanel;
	
	public EIATypeComponentSubTab(EIATypeTab tab) {
		super("Componentes",tab);
		
		partesGridPanel = new EIATypeComponentGridPanel();
		addGHAClosableHandler(partesGridPanel);
		addGHAHideableHandler(partesGridPanel);		
		
		setPane(partesGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		partesGridPanel.select(eiaType);
	}
}
