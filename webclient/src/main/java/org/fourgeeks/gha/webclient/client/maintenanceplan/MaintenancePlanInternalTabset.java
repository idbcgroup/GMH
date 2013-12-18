package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.AsociatedEiaTypeSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedtype.AsociatedEiaSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.information.MaintenancePlanInformationSubTab;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.tab.Tab;

public class MaintenancePlanInternalTabset extends GHAInternalTabSet implements
		ResizeHandler, MaintenancePlanSelectionListener {

	private final MaintenancePlanInformationSubTab maintenancePlanInformationSubTab;
	private final AsociatedEiaTypeSubTab maintenancePlanEquipmentTypeSubTab;
	private final AsociatedEiaSubTab maintenancePlanEquipmentSubTab;

	// private final MaintenanceProtocolSubTab maintenanceProtocolSubTab;

	public MaintenancePlanInternalTabset(MaintenancePlanPanel mpTab) {
		super(mpTab);
		maintenancePlanInformationSubTab = new MaintenancePlanInformationSubTab(
				mpTab);
		maintenancePlanEquipmentTypeSubTab = new AsociatedEiaTypeSubTab(mpTab);
		maintenancePlanEquipmentSubTab = new AsociatedEiaSubTab(mpTab);
		// maintenanceProtocolSubTab = new MaintenanceProtocolSubTab(mpTab);

		// Agregando las Subtabs
		addTab(maintenancePlanInformationSubTab);
		addTab(maintenancePlanEquipmentTypeSubTab);
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
		else if (selectedTab == maintenancePlanEquipmentTypeSubTab) {
			maintenancePlanEquipmentTypeSubTab.getPane().show();
		}
		else if (selectedTab == maintenancePlanEquipmentSubTab) {
			maintenancePlanEquipmentSubTab.getPane().show();
		}
		animateShow(AnimationEffect.FADE);
	}
}
