package org.fourgeeks.gha.webclient.client.eia.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAMaintPlanGridPanel extends VLayout implements EIATypeSelectionListener, GHAClosable, GHAHideable{
	
	private EIAMaintPlanGrid eiaTypeMaintenancePlanGrid = new EIAMaintPlanGrid();
	
	public EIAMaintPlanGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		
		Label title = new Label("<h3>Planes de Mantenimiento</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
	
		VLayout sideButtons = GHAUiHelper.createBar(
	    		new GHAImgButton("../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						
					}
				}),
	    		new GHAImgButton("../resources/icons/edit.png"),
	    		new GHAImgButton("../resources/icons/delete.png"),
	    		GHAUiHelper.verticalGraySeparator("2px"),
	    		new GHAImgButton("../resources/icons/set.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						
					}
				}));
		
	    HLayout mainPanel = new HLayout();
		mainPanel.addMembers(eiaTypeMaintenancePlanGrid, sideButtons);
		
	    addMembers(title,mainPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
