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
	 * @param panel
	 */
	public EIATypeMaintenanceSubTab(EIATypePanel panel) {
		super(GHAStrings.get("maintenance"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		eiaTypeMaintenanceGridPanel = new EIATypeMaintenanceGridPanel();
		addClosableListener(eiaTypeMaintenanceGridPanel);
		addHideableListener(eiaTypeMaintenanceGridPanel);

		setPane(eiaTypeMaintenanceGridPanel);
		panel.addEiaTypeSelectionListener(eiaTypeMaintenanceGridPanel);
	}
}
