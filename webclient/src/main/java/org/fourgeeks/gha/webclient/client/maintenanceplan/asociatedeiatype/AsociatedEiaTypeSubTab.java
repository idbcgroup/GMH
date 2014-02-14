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

	/**
	 * @param panel
	 */
	public AsociatedEiaTypeSubTab(MaintenancePlanPanel panel) {
		super(GHAStrings.get("asociated-eiatype-maintenance-plan"));
		panel.addHideableListener(this);
		panel.addClosableListener(this);
		eiatypeGridPanel = new AsociatedEiatypeGridPanel();
		addClosableListener(eiatypeGridPanel);
		addHideableListener(eiatypeGridPanel);

		setPane(eiatypeGridPanel);
		panel.addMaintenancePlanSelectionListener(this);
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		eiatypeGridPanel.select(maintenancePlan);
	}

}