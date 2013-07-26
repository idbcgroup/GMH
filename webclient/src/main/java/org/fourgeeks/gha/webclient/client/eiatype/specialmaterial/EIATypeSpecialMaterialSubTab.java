package org.fourgeeks.gha.webclient.client.eiatype.specialmaterial;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIATypeSpecialMaterialSubTab extends Tab 
		implements EIATypeSelectionListener, GHAClosable{
	
	private EIATypeSpecialMaterialGridPanel eiaTypeSpecialMaterialGridPanel = new EIATypeSpecialMaterialGridPanel();
	
	public EIATypeSpecialMaterialSubTab() {
		setTitle("Materiales Especiales");
		setPaneMargin(0);
		
		setPane(eiaTypeSpecialMaterialGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}
