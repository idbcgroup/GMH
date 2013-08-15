package org.fourgeeks.gha.webclient.client.eia.material;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIATab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

public class EIAMaterialSubTab extends GHASubTab implements
		EIATypeSelectionListener {

	private EIAMaterialGridPanel eiaMaterialGridPanel;

	public EIAMaterialSubTab(EIATab tab) {
		super("Materiales", tab);

		eiaMaterialGridPanel = new EIAMaterialGridPanel();
		setPane(eiaMaterialGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub

	}
}
