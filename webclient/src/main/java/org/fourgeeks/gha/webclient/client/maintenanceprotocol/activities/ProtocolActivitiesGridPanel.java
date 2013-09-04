package org.fourgeeks.gha.webclient.client.maintenanceprotocol.activities;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityGrid;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class ProtocolActivitiesGridPanel extends VLayout implements GHAClosable,GHAHideable{

	private MaintenanceActivityGrid maintenanceActivityGrid = new MaintenanceActivityGrid();
		
	public ProtocolActivitiesGridPanel() {
		super();
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setBackgroundColor("#E0E0E0");
		
		Label title = new Label("<h3>Actividades del Protocolo</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
		
		VLayout sideButtons = GHAUiHelper.createBar(
				new GHAImgButton("../resources/icons/new.png"),
				new GHAImgButton("../resources/icons/edit.png"),
	    		new GHAImgButton("../resources/icons/delete.png")
	    		);
		
		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(maintenanceActivityGrid, sideButtons);
	    
		addMembers(title,mainPanel);
	}
	
	@Override
	public void close() {
		//TODO:
	}

	@Override
	public void hide() {
		super.hide();
	}
}
