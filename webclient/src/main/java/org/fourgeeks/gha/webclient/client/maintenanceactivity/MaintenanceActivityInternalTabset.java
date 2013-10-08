package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.information.MaintenanceActivityInformationSubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

public class MaintenanceActivityInternalTabset extends TabSet implements ResizeHandler {

	private MaintenanceActivityInformationSubTab maintenanceActivityInformationSubTab;

	public MaintenanceActivityInternalTabset(MaintenanceActivityTab mpTab) {
		super();
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight());

		GHAUiHelper.addGHAResizeHandler(this);
		maintenanceActivityInformationSubTab = new MaintenanceActivityInformationSubTab(mpTab);

		// Agregando las Subtabs
		addTab(maintenanceActivityInformationSubTab);

	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}
}
