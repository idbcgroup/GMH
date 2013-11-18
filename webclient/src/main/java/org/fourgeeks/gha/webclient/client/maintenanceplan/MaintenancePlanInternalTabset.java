package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAInternalTabSet;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.AsociatedEiaTypeSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.information.MaintenancePlanInformationSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.MaintenanceProtocolSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;

public class MaintenancePlanInternalTabset extends GHAInternalTabSet implements
		ResizeHandler, MaintenancePlanSelectionListener {

	private MaintenancePlanInformationSubTab maintenancePlanInformationSubTab;
	private AsociatedEiaTypeSubTab maintenancePlanEquipmentSubTab;
	private MaintenanceProtocolSubTab maintenanceProtocolSubTab;

	public MaintenancePlanInternalTabset(MaintenancePlanTab mpTab) {
		super(mpTab);
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());

		GHAUiHelper.addGHAResizeHandler(this);

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
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		animateShow(AnimationEffect.FADE);
	}
}
