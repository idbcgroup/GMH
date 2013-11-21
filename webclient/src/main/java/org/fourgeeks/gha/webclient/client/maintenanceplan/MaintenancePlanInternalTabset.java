package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.AsociatedEiaTypeSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.information.MaintenancePlanInformationSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.MaintenanceProtocolSubTab;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;

public class MaintenancePlanInternalTabset extends GHAInternalTabSet implements
		ResizeHandler, MaintenancePlanSelectionListener {

	private final MaintenancePlanInformationSubTab maintenancePlanInformationSubTab;
	private final AsociatedEiaTypeSubTab maintenancePlanEquipmentSubTab;
	private final MaintenanceProtocolSubTab maintenanceProtocolSubTab;

	public MaintenancePlanInternalTabset(MaintenancePlanTab mpTab) {
		super(mpTab);
		maintenancePlanInformationSubTab = new MaintenancePlanInformationSubTab(
				mpTab);
		maintenancePlanEquipmentSubTab = new AsociatedEiaTypeSubTab(mpTab);
		maintenanceProtocolSubTab = new MaintenanceProtocolSubTab(mpTab);

		// Agregando las Subtabs
		addTab(maintenancePlanInformationSubTab);
		addTab(maintenancePlanEquipmentSubTab);
		addTab(maintenanceProtocolSubTab);

	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		animateShow(AnimationEffect.FADE);
	}
}
