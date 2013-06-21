package org.fourgeeks.gha.webclient.client.eiatype.equipos;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.tab.Tab;

public class EIATypeEquiposSubTab extends Tab implements
		EIATypeSelectionListener {

	private EIATypeEquiposGridPanel equiposGridPanel = new EIATypeEquiposGridPanel();

	public EIATypeEquiposSubTab() {
		setTitle("Equipos");
		setPaneMargin(0);
		setPane(equiposGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		equiposGridPanel.select(eiaType);
	}
}