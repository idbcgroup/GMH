package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.information.MaintenanceActivitySubTab;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.tab.TabSet;

public class MaintenanceActivityInternalTabset extends TabSet implements ResizeHandler {

	private MaintenanceActivitySubTab maintenanceActivityInformationSubTab;

	public MaintenanceActivityInternalTabset(MaintenanceActivityTab mpTab) {
		super();
		setWidth100();
		setMinWidth(1024);
		setHeight(GHAUiHelper.getBottomSectionHeight());

		GHAUiHelper.addGHAResizeHandler(this);
		maintenanceActivityInformationSubTab = new MaintenanceActivitySubTab(mpTab);

		// Agregando las Subtabs
		addTab(maintenanceActivityInformationSubTab);

	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}
}
