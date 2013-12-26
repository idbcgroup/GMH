package org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAEditButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenancePlanMaintenanceProtocolGrid;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class EIATypeMaintenanceProtocolForm extends GHASlideInWindow implements
		ClosableListener, HideableListener, ResizeHandler {

	private MaintenancePlanMaintenanceProtocolGrid maintenanceProtocolGrid = new MaintenancePlanMaintenanceProtocolGrid();

	public EIATypeMaintenanceProtocolForm() {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setMinWidth(1024);
		setHeight(GHAUiHelper.getBottomSectionHeight()-5);
		setTop(260);

		Label title = new Label("Protocolos del Plan");

		VLayout sideButtons = GHAUiHelper.createBar(new GHAEditButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub

					}
				}), new GHADeleteButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}), GHAUiHelper.verticalGraySeparator("2px"), new GHACancelButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						hide();
					}
				}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(maintenanceProtocolGrid, sideButtons);

		addMembers(title, mainPanel);
	}

	@Override
	public void close() {
		destroy();
	}

	@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		setHeight(GHAUiHelper.getBottomSectionHeight()-5);
	}
}