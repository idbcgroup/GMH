package org.fourgeeks.gha.webclient.client.eia.material;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIATab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

public class EIAMaterialSubTab extends GHASubTab implements EIATypeSelectionListener{
	
	private EIAMaterialGridPanel eiaMaterialsGridPanel = null;
	
	public EIAMaterialSubTab(EIATab tab) {
		super("Materiales", tab);
		
		eiaMaterialsGridPanel = new EIAMaterialGridPanel();
		addGHAClosableHandler(eiaMaterialsGridPanel);
		addGHAHideableHandler(eiaMaterialsGridPanel);
		
		setPane(eiaMaterialsGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}
}
