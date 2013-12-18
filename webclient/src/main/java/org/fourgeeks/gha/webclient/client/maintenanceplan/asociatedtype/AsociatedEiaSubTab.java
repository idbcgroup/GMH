package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedtype;

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
public class AsociatedEiaSubTab extends GHASubTab implements
		MaintenancePlanSelectionListener {

	private final AsociatedEiaGridPanel eiaGridPanel;

	public AsociatedEiaSubTab(MaintenancePlanPanel tab) {
		super(GHAStrings.get("eia"), tab);

		eiaGridPanel = new AsociatedEiaGridPanel();
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