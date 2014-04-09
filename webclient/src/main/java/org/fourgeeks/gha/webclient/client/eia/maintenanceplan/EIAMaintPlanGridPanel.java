package org.fourgeeks.gha.webclient.client.eia.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.imageitems.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATopTitleLabel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAMaintPlanGridPanel extends VLayout implements
EIATypeSelectionListener, ClosableListener, HideableListener {

	private EIAMaintPlanGrid eiaTypeMaintenancePlanGrid = new EIAMaintPlanGrid();

	public EIAMaintPlanGridPanel() {
		setWidth100();
		setMinWidth(GHAUiHelper.MIN_WIDTH);
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!

		GHATopTitleLabel title = new GHATopTitleLabel("Planes de Mantenimiento");

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {

					}
				}), new GHAImgButton("../resources/icons/edit.png"),
				new GHAImgButton("../resources/icons/delete.png"), GHAUiHelper
				.verticalGraySeparator("2px"), new GHAImgButton(
						"../resources/icons/set.png", new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {

							}
						}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(eiaTypeMaintenancePlanGrid, sideButtons);

		addMembers(title, mainPanel);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub

	}
}
