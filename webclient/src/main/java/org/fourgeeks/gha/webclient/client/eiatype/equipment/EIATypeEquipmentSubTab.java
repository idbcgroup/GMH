package org.fourgeeks.gha.webclient.client.eiatype.equipment;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
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
		super(GHAStrings.get("equipments"), tab);

		equiposGridPanel = new EIATypeEquipmentGridPanel();
		addClosableListener(equiposGridPanel);
		addHideableListener(equiposGridPanel);

		setPane(equiposGridPanel);

		tab.addEiaTypeSelectionListener(this);
	}

	@Override
	public void select(EiaType eiaType) {
		equiposGridPanel.select(eiaType);
	}
}