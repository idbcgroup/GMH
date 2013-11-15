package org.fourgeeks.gha.webclient.client.eiatype.equipment;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeTab;

/**
 * @author alacret
 * 
 */
public class EIATypeEquipmentSubTab extends GHASubTab {

	private EIATypeEquipmentGridPanel equiposGridPanel;

	/**
	 * @param tab
	 */
	public EIATypeEquipmentSubTab(EIATypeTab tab) {
		super(GHAStrings.get("equipments"), tab);

		equiposGridPanel = new EIATypeEquipmentGridPanel();
		addClosableListener(equiposGridPanel);
		addHideableListener(equiposGridPanel);

		setPane(equiposGridPanel);

		tab.addEiaTypeSelectionListener(equiposGridPanel);
	}

}