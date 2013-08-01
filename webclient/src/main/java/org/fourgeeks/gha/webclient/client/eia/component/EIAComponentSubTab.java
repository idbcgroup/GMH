package org.fourgeeks.gha.webclient.client.eia.component;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIATab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

public class EIAComponentSubTab extends GHASubTab implements EIATypeSelectionListener{
	
	private EIAComponentGridPanel eiaComponentGridPanel = null;
	
	public EIAComponentSubTab(EIATab tab) {
		super("Componentes", tab);
		
		eiaComponentGridPanel = new EIAComponentGridPanel();
		addGHAClosableHandler(eiaComponentGridPanel);
		addGHAHideableHandler(eiaComponentGridPanel);
		
		setPane(eiaComponentGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
	}
}
