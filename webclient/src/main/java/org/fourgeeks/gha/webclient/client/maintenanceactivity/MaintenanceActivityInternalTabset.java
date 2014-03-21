package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.information.MaintenanceActivitySubTab;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource.MaintenanceActivityServiceAndResourceSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol.MaintenanceSubprotocolActivitiesSubTab;

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

	private final MaintenanceActivitySubTab maintenanceActivitySubTab;
	private final MaintenanceSubprotocolActivitiesSubTab subprotocolActivitiesSubTab;
	private final MaintenanceActivityServiceAndResourceSubTab serviceAndResourceSubTab;

	/**
	 * @param panel
	 */
	public MaintenanceActivityInternalTabset(MaintenanceActivityPanel panel) {
		super();
		setVisible(false);
		maintenanceActivitySubTab = new MaintenanceActivitySubTab(panel);
		subprotocolActivitiesSubTab = new MaintenanceSubprotocolActivitiesSubTab(
				panel);
		serviceAndResourceSubTab = new MaintenanceActivityServiceAndResourceSubTab(
				panel);

		maintenanceActivitySubTab
				.addMaintenanceActivitySubProtocolListener(subprotocolActivitiesSubTab);

		// Agregando las Subtabs
		addTab(maintenanceActivitySubTab);
		addTab(subprotocolActivitiesSubTab);
		addTab(serviceAndResourceSubTab);
	}

	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		show();
	}

	@Override
	public void show() {
		final Tab selectedTab = getSelectedTab();

		if (selectedTab == maintenanceActivitySubTab)
			maintenanceActivitySubTab.show();
		else if (selectedTab == subprotocolActivitiesSubTab) {
			subprotocolActivitiesSubTab.getPane().show();
		} else if (selectedTab == serviceAndResourceSubTab) {
			serviceAndResourceSubTab.openFirstSection();
		}

		animateShow(AnimationEffect.FADE);
	}
}
