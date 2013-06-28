package org.fourgeeks.gha.webclient.client.eiatype.component;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIATypePartesSubTab extends Tab implements EIATypeSelectionListener, GHAClosable{
	
	private EIATypePartesGridPanel partesGridPanel = new EIATypePartesGridPanel();
	
	public EIATypePartesSubTab() {
		setTitle("Partes/Componentes");
		setPaneMargin(0);
		
		setPane(partesGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		partesGridPanel.select(eiaType);
	}

	@Override
	public void close() {
		partesGridPanel.close();
	}
}
