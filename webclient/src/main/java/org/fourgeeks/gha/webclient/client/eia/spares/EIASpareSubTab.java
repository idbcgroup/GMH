package org.fourgeeks.gha.webclient.client.eia.spares;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIATab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

public class EIASpareSubTab extends GHASubTab implements EIATypeSelectionListener{
	
	private EIASpareGridPanel eiaSpareGridPanel = null;
	
	public EIASpareSubTab(EIATab tab) {
		super("Repuestos", tab);
		
		eiaSpareGridPanel = new EIASpareGridPanel();
		addGHAClosableHandler(eiaSpareGridPanel);
		addGHAHideableHandler(eiaSpareGridPanel);
		
		setPane(eiaSpareGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
