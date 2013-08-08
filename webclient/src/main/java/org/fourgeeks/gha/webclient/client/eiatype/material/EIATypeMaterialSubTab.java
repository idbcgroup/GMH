package org.fourgeeks.gha.webclient.client.eiatype.material;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

public class EIATypeMaterialSubTab extends GHASubTab implements
		EIATypeSelectionListener {

	private EIATypeMaterialGridPanel eiaTypeMaterialGridPanel;

	public EIATypeMaterialSubTab(EIATypeTab tab) {
		super("Materiales", tab);

		eiaTypeMaterialGridPanel = new EIATypeMaterialGridPanel();
		setPane(eiaTypeMaterialGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub

	}
}
