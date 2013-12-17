package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.AsociatedEiaTypeSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.information.MaintenancePlanInformationSubTab;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.tab.Tab;

public class MaintenancePlanInternalTabset extends GHAInternalTabSet implements
		ResizeHandler, MaintenancePlanSelectionListener {

	private final MaintenancePlanInformationSubTab maintenancePlanInformationSubTab;
	private final AsociatedEiaTypeSubTab maintenancePlanEquipmentSubTab;

	// private final MaintenanceProtocolSubTab maintenanceProtocolSubTab;

	public MaintenancePlanInternalTabset(MaintenancePlanPanel mpTab) {
		super(mpTab);
		maintenancePlanInformationSubTab = new MaintenancePlanInformationSubTab(
				mpTab);
		maintenancePlanEquipmentSubTab = new AsociatedEiaTypeSubTab(mpTab);
		// maintenanceProtocolSubTab = new MaintenanceProtocolSubTab(mpTab);

		// Agregando las Subtabs
		addTab(maintenancePlanInformationSubTab);
		addTab(maintenancePlanEquipmentSubTab);
		// addTab(maintenanceProtocolSubTab);

	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		show();
	}

	@Override
	public void show() {
		Tab selectedTab = getSelectedTab();
		if (selectedTab == maintenancePlanInformationSubTab)
			maintenancePlanInformationSubTab.show();
		else if (selectedTab == maintenancePlanEquipmentSubTab) {
			maintenancePlanEquipmentSubTab.getPane().show();
		}
		animateShow(AnimationEffect.FADE);
	}
}
