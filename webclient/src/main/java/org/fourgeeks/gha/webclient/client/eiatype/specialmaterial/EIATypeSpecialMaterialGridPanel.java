package org.fourgeeks.gha.webclient.client.eiatype.specialmaterial;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeSpecialMaterialGridPanel extends VLayout implements EIATypeSelectionListener{

	private EIATypeSpecialMaterialGrid eiaTypeSpecialMaterialGrid = new EIATypeSpecialMaterialGrid();
	
	public EIATypeSpecialMaterialGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		
		Label title = new Label("<h3>Materiales Especiales</h3>");
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
		mainPanel.addMembers(eiaTypeSpecialMaterialGrid, sideButtons);
	    
		addMembers(title, mainPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

}