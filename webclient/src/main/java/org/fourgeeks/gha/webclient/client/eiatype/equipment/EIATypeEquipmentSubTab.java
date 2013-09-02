package org.fourgeeks.gha.webclient.client.eiatype.equipment;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

public class EIATypeEquipmentSubTab extends GHASubTab implements
		EIATypeSelectionListener, EIASelectionListener {

	private EIATypeEquipmentGridPanel equiposGridPanel;

	public EIATypeEquipmentSubTab(EIATypeTab tab) {
		super("Equipos", tab);
		tab.addEiaTypeSelectionListener(this);
		equiposGridPanel = new EIATypeEquipmentGridPanel(this);
		addGHAClosableHandler(equiposGridPanel);
		addGHAHideableHandler(equiposGridPanel);

		setPane(equiposGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		equiposGridPanel.select(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.EIASelectionListener#select(org
	 * .fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void select(Eia eia) {
		equiposGridPanel.select(eia);

	}
}