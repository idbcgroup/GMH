package org.fourgeeks.gha.webclient.client.eiatype.equipment;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

/**
 * @author alacret
 * 
 */
public class EIATypeEquipmentSubTab extends GHASubTab implements
		EIATypeSelectionListener {

	private EIATypeEquipmentGridPanel equiposGridPanel;

	/**
	 * @param tab
	 */
	public EIATypeEquipmentSubTab(EIATypeTab tab) {
		super("Equipos", tab);
		setDisabled(true);
		tab.addEiaTypeSelectionListener(this);
		equiposGridPanel = new EIATypeEquipmentGridPanel(this);
		addGHAClosableHandler(equiposGridPanel);
		addGHAHideableHandler(equiposGridPanel);

		setPane(equiposGridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		equiposGridPanel.select(eiaType);
		setDisabled(false);
	}
}