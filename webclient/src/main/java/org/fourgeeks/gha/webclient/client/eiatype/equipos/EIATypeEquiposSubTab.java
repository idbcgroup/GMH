package org.fourgeeks.gha.webclient.client.eiatype.equipos;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

import com.smartgwt.client.widgets.tab.Tab;

public class EIATypeEquiposSubTab extends Tab implements
		EIATypeSelectionListener {

	private EIATypeEquiposGridPanel equiposGridPanel = new EIATypeEquiposGridPanel();

	public EIATypeEquiposSubTab(EIATypeTab tab) {
		setTitle("Equipos");
		setPaneMargin(0);
		setPane(equiposGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		equiposGridPanel.select(eiaType);
	}
}