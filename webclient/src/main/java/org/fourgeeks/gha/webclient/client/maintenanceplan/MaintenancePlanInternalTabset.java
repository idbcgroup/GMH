package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.AsociatedEiaTypeSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.information.MaintenancePlanInformationSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocols.MaintenanceProtocolsSubTab;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.tab.Tab;

/**
 * @author emiliot, naramirez
 */
public class MaintenancePlanInternalTabset extends GHAInternalTabSet implements
		ResizeHandler, MaintenancePlanSelectionListener {

	private final MaintenancePlanInformationSubTab maintenancePlanInfoSubTab;
	private final AsociatedEiaTypeSubTab maintenancePlanEquipmentSubTab;
	private final MaintenanceProtocolsSubTab maintenancePlanProtocolsSubTab;

	/**
	 * @param panel
	 */
	public MaintenancePlanInternalTabset(MaintenancePlanPanel panel) {
		super(panel);
		maintenancePlanInfoSubTab = new MaintenancePlanInformationSubTab(panel);
		maintenancePlanEquipmentSubTab = new AsociatedEiaTypeSubTab(panel);
		maintenancePlanProtocolsSubTab = new MaintenanceProtocolsSubTab(panel);

		maintenancePlanProtocolsSubTab
				.addMaintenanceProtocolsSelectionListener(maintenancePlanInfoSubTab);

		// Agregando las Subtabs
		addTab(maintenancePlanInfoSubTab);
		addTab(maintenancePlanProtocolsSubTab);
		addTab(maintenancePlanEquipmentSubTab);

	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		show();
	}

	@Override
	public void show() {
		Tab selectedTab = getSelectedTab();
		if (selectedTab == maintenancePlanInfoSubTab)
			maintenancePlanInfoSubTab.show();
		else if (selectedTab == maintenancePlanEquipmentSubTab)
			maintenancePlanEquipmentSubTab.getPane().show();
		else if (selectedTab == maintenancePlanProtocolsSubTab)
			maintenancePlanProtocolsSubTab.getPane().show();

		animateShow(AnimationEffect.FADE);
	}
}
