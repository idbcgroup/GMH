package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.activities.ProtocolActivitiesSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.asociatedmaintenanceplan.AsociatedMaintenancePlanSubTab;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.information.MaintenanceProtocolInformationSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

public class MaintenanceProtocolInternalTabset extends TabSet implements ResizeHandler {

	private MaintenanceProtocolInformationSubTab maintenanceProtocolInformationSubTab;
	private AsociatedMaintenancePlanSubTab asociatedMaintenancePlanSubTab;
	private ProtocolActivitiesSubTab protocolActivitySubTab;
		
	public MaintenanceProtocolInternalTabset(MaintenanceProtocolTab mpTab) {
		super();
		setWidth100();
		setMinWidth(1024);
		setHeight(GHAUiHelper.getBottomSectionHeight());

		GHAUiHelper.addGHAResizeHandler(this);

		maintenanceProtocolInformationSubTab = new MaintenanceProtocolInformationSubTab(mpTab);
		asociatedMaintenancePlanSubTab = new AsociatedMaintenancePlanSubTab(mpTab);
		protocolActivitySubTab = new ProtocolActivitiesSubTab(mpTab);
		
		// Agregando las Subtabs
		addTab(maintenanceProtocolInformationSubTab);
		addTab(asociatedMaintenancePlanSubTab);
		addTab(protocolActivitySubTab);

	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

}
