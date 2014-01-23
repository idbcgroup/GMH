package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.tab.Tab;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceActivityInternalTabset extends GHAInternalTabSet implements
		ResizeHandler, MaintenanceActivitySelectionListener {

	// private final MaintenanceProtocolsSubTab maintenancePlanProtocolsSubTab;
	// private final MaintenanceActivitySubTab maintenanceActivitySubTab;

	/**
	 * @param panel
	 */
	public MaintenanceActivityInternalTabset(MaintenanceActivityPanel panel) {
		super(panel);
		// maintenancePlanProtocolsSubTab = new
		// MaintenanceProtocolsSubTab(panel);
		// maintenanceActivitySubTab = new MaintenanceActivitySubTab(panel);
		//
		// maintenancePlanProtocolsSubTab
		// .addMaintenanceProtocolsSelectionListener(maintenancePlanInfoSubTab);
		//
		// // Agregando las Subtabs
		// addTab(maintenancePlanProtocolsSubTab);
		// addTab(maintenanceActivitySubTab);
	}

	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		show();
	}

	@Override
	public void show() {
		Tab selectedTab = getSelectedTab();
		// if (selectedTab == maintenancePlanProtocolsSubTab) {
		// maintenancePlanProtocolsSubTab.getPane().show();
		// } else if (selectedTab == maintenanceActivitySubTab) {
		// maintenanceActivitySubTab.openFirstSection();
		// }

		animateShow(AnimationEffect.FADE);
	}
}
