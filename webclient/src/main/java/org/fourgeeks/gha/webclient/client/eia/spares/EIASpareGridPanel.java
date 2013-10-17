package org.fourgeeks.gha.webclient.client.eia.spares;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIASpareGridPanel extends VLayout implements GHAClosable,GHAHideable{

	private EIASpareGrid eiaSpareGrid = new EIASpareGrid();
	
	public EIASpareGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		
		
		GHALabel title = new GHALabel("Repuestos");
				
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
        mainPanel.addMembers(eiaSpareGrid,sideButtons);
        
		addMembers(title, mainPanel);
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
