package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.information.MaintenanceActivitySubTab;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocolactivities.MaintenanceSubprotocolActivitiesSubTab;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.tab.Tab;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceActivityInternalTabset extends GHAInternalTabSet
		implements ResizeHandler, MaintenanceActivitySelectionListener {

	// private final MaintenanceProtocolsSubTab maintenancePlanProtocolsSubTab;
	private final MaintenanceActivitySubTab maintenanceActivitySubTab;
	private final MaintenanceSubprotocolActivitiesSubTab subprotocolActivitiesSubTab;

	/**
	 * @param panel
	 */
	public MaintenanceActivityInternalTabset(MaintenanceActivityPanel panel) {
		super(panel);

		maintenanceActivitySubTab = new MaintenanceActivitySubTab(panel);
		subprotocolActivitiesSubTab = new MaintenanceSubprotocolActivitiesSubTab(
				panel);

		maintenanceActivitySubTab
				.addMaintenanceActivitySubProtocolListener(subprotocolActivitiesSubTab);

		// maintenancePlanProtocolsSubTab = new
		// MaintenanceProtocolsSubTab(panel);
		// maintenancePlanProtocolsSubTab.addMaintenanceProtocolsSelectionListener(maintenancePlanInfoSubTab);

		// // Agregando las Subtabs
		addTab(maintenanceActivitySubTab);
		addTab(subprotocolActivitiesSubTab);
		// addTab(maintenancePlanProtocolsSubTab);
	}

	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		show();
	}

	@Override
	public void show() {
		Tab selectedTab = getSelectedTab();

		if (selectedTab == maintenanceActivitySubTab)
			maintenanceActivitySubTab.show();
		else if (selectedTab == subprotocolActivitiesSubTab) {
			subprotocolActivitiesSubTab.getPane().show();
		}

		animateShow(AnimationEffect.FADE);
	}
}
