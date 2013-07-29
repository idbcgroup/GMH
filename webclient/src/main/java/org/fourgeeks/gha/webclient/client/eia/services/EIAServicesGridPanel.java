package org.fourgeeks.gha.webclient.client.eia.services;

import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAServicesGridPanel extends VLayout implements GHAClosable {

	private EIAServicesGrid eiaServicesGrid = new EIAServicesGrid();
	
	public EIAServicesGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		
		
		Label title = new Label("<h3>Servicios de EIA</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
				
// //////Botones laterales
        VLayout sideButtons = GHAUiHelper.createBar(
	    		new GHAImgButton("../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
//						form.animateShow(AnimationEffect.FLY);
					}
				}),
	    		new GHAImgButton("../resources/icons/edit.png"),
	    		new GHAImgButton("../resources/icons/delete.png"),
	    		new GHAImgButton("../resources/icons/set.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
//						EIARecord selectedRecord = (EIARecord) eiaTypeGrid.getSelectedRecord();
//						History.newItem("eia/" + selectedRecord.getCode());
					}
				}));
		
        HLayout mainPanel = new HLayout();
        mainPanel.addMembers(eiaServicesGrid,sideButtons);
        
		addMembers(title, mainPanel);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
