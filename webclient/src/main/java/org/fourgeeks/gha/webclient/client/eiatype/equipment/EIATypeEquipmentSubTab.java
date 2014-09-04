package org.fourgeeks.gha.webclient.client.eiatype.equipment;

import org.fourgeeks.gha.domain.gmh.GlaLog;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePanel;

/**
 * @author alacret
 * 
 */
public class EIATypeEquipmentSubTab extends GHASubTab implements
		EiaDamageReportSelectionListener {

	private final EIATypeEquipmentGridPanel equiposGridPanel;

	/**
	 * @param panel
	 */
	public EIATypeEquipmentSubTab(EIATypePanel panel) {
		super(GHAStrings.get("equipments"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		equiposGridPanel = new EIATypeEquipmentGridPanel();
		addClosableListener(equiposGridPanel);
		addHideableListener(equiposGridPanel);

		setPane(equiposGridPanel);

		panel.addEiaTypeSelectionListener(equiposGridPanel);
	}

	@Override
	public void select(GlaLog glaLog) {
		equiposGridPanel.select(glaLog);
	}

}