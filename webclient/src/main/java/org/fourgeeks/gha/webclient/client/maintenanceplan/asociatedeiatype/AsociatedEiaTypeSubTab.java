package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHASubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanPanel;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;

/**
 * @author emiliot
 * 
 */
public class AsociatedEiaTypeSubTab extends GHASubTab implements
		MaintenancePlanSelectionListener {

	private final AsociatedEiatypeGridPanel eiatypeGridPanel;

	public AsociatedEiaTypeSubTab(MaintenancePlanPanel tab) {
		super(GHAStrings.get("asociated-eiatype-maintenance-plan"), tab);

		eiatypeGridPanel = new AsociatedEiatypeGridPanel();
		addClosableListener(eiatypeGridPanel);
		addHideableListener(eiatypeGridPanel);

		setPane(eiatypeGridPanel);
		tab.addMaintenancePlanSelectionListener(this);
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		eiatypeGridPanel.select(maintenancePlan);
	}

}