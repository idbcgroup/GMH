package org.fourgeeks.gha.webclient.client.maintenanceactivity.activities;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityGrid;

import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */

public class ActivitiesGridPanel extends VLayout implements ClosableListener,
		HideableListener {

	private final MaintenanceActivityGrid maintenanceActivityGrid = new MaintenanceActivityGrid();

	public ActivitiesGridPanel() {
		super();
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setMinWidth(1024);
		setBackgroundColor("#E0E0E0");

		GHALabel title = new GHALabel("Sub-Actividades");

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/new.png"), new GHAImgButton(
				"../resources/icons/edit.png"), new GHAImgButton(
				"../resources/icons/delete.png"));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(maintenanceActivityGrid, sideButtons);

		addMembers(title, mainPanel);
	}

	@Override
	public void close() {
		// TODO:
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}
}
