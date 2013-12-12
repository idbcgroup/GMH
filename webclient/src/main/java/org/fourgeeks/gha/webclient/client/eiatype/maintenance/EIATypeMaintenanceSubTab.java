package org.fourgeeks.gha.webclient.client.eiatype.maintenance;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypePanel;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.EIATypeMaintenanceGridPanel;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATypeMaintenanceSubTab extends GHASubTab {

	private EIATypeMaintenanceGridPanel eiaTypeMaintenanceGridPanel;

	/**
	 * @param tab
	 */
	public EIATypeMaintenanceSubTab(EIATypePanel tab) {
		super(GHAStrings.get("maintenance"), tab);

		eiaTypeMaintenanceGridPanel = new EIATypeMaintenanceGridPanel();
		addClosableListener(eiaTypeMaintenanceGridPanel);
		addHideableListener(eiaTypeMaintenanceGridPanel);

		setPane(eiaTypeMaintenanceGridPanel);
		tab.addEiaTypeSelectionListener(eiaTypeMaintenanceGridPanel);
	}
}
