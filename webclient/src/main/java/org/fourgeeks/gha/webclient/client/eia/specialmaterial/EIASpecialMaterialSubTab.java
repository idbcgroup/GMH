package org.fourgeeks.gha.webclient.client.eia.specialmaterial;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIASpecialMaterialSubTab extends Tab implements EIATypeSelectionListener, GHAClosable {
	
	private EIASpecialMaterialGridPanel specialMaterialGridPanel = new EIASpecialMaterialGridPanel();
	
	public EIASpecialMaterialSubTab() {
		setTitle("Material Especial");
		setPaneMargin(0);
		
		setPane(specialMaterialGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		specialMaterialGridPanel.close();
	}
}
