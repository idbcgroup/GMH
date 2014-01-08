package org.fourgeeks.gha.webclient.client.maintenanceplan.eianoservice;

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
public class EiaNoServiceMaintenanceSubTab extends GHASubTab implements
		MaintenancePlanSelectionListener {

	private final EiaNoServiceMaintenanceGridPanel eiaGridPanel;

	/**
	 * 
	 * @param tab
	 */
	public EiaNoServiceMaintenanceSubTab(MaintenancePlanPanel tab) {
		super(GHAStrings.get("eia-on-maintenance"), tab);

		eiaGridPanel = new EiaNoServiceMaintenanceGridPanel();
		addClosableListener(eiaGridPanel);
		addHideableListener(eiaGridPanel);

		setPane(eiaGridPanel);
		tab.addMaintenancePlanSelectionListener(this);
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		eiaGridPanel.select(maintenancePlan);
	}

}