package org.fourgeeks.gha.webclient.client.eia.cost;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIATab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

public class EIACostSubTab extends GHASubTab implements EIATypeSelectionListener {
	
	private EIACostGridPanel eiaCostGridPanel = null;
	
	public EIACostSubTab(EIATab tab) {
		super("Costos y Depreciación", tab);
		
		eiaCostGridPanel = new EIACostGridPanel();
		addGHAClosableHandler(eiaCostGridPanel);
		addGHAHideableHandler(eiaCostGridPanel);
		
		setPane(eiaCostGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
