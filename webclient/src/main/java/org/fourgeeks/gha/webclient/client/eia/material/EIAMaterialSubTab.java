package org.fourgeeks.gha.webclient.client.eia.material;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIATab;

public class EIAMaterialSubTab extends GHASubTab implements
		EIASelectionListener {

	private EIAMaterialGridPanel eiaMaterialGridPanel;

	public EIAMaterialSubTab(EIATab tab) {
		super("Materiales", tab);
		setDisabled(true);
		tab.addEiaSelectionListener(this);
		
		eiaMaterialGridPanel = new EIAMaterialGridPanel();
		setPane(eiaMaterialGridPanel);
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub
		setDisabled(false);
	}
}
