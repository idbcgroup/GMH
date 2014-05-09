package org.fourgeeks.gha.webclient.client.maintenanceplan.eiaplanification;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPanel;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;

/**
 * 
 * @author caparicio
 * 
 */
public class EiaPlanificationSubTab extends GHASubTab implements
		MaintenancePlanSelectionListener {

	private final EiaPlanificationGridPanel eiaGridPanel;

	/**
	 * 
	 * @param panel
	 */
	public EiaPlanificationSubTab(MaintenancePlanPanel panel) {
		super(GHAStrings.get("eia-maintenance-plan"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);

		eiaGridPanel = new EiaPlanificationGridPanel();
		addClosableListener(eiaGridPanel);
		addHideableListener(eiaGridPanel);

		setPane(eiaGridPanel);
		panel.addMaintenancePlanSelectionListener(this);
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		eiaGridPanel.select(maintenancePlan);
	}

	public EiaPlanificationGridPanel getEiaGridPanel() {
		return eiaGridPanel;
	}
}