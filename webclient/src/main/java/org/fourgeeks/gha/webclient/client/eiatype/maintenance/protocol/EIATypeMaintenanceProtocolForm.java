package org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol;

import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

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
public class EIATypeMaintenanceProtocolForm extends GHASlideInWindow implements GHAClosable,GHAHideable, ResizeHandler{

	private EIATypeMaintenanceProtocolGrid eiaTypeMaintenanceProtocolGrid = new EIATypeMaintenanceProtocolGrid();
		
	public EIATypeMaintenanceProtocolForm() {
		GHAUiHelper.addGHAResizeHandler(this);
		setWidth100();
		setHeight(GHAUiHelper.getBottomSectionHeight()-20);
		setTop(260);
		
		Label title = new Label("<h3>Protocolos del Plan</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
		
		VLayout sideButtons = GHAUiHelper.createBar(
				new GHAImgButton("../resources/icons/edit.png"),
	    		new GHAImgButton("../resources/icons/delete.png"),
	    		GHAUiHelper.verticalGraySeparator("2px"),
	    		new GHAImgButton("../resources/icons/cancel.png",
						new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						hide();
					}
				}));
		
		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(eiaTypeMaintenanceProtocolGrid, sideButtons);
	    
		addMembers(title,mainPanel);
	}
	
	@Override
	public void close() {
		destroy();
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight()-20);
	}
}