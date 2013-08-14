package org.fourgeeks.gha.webclient.client.eia.cost;

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
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIACostGridPanel extends VLayout implements EIATypeSelectionListener, GHAClosable,GHAHideable{
	
	private EIADeprecationGrid eiaDeprecationGrid = new EIADeprecationGrid();
	
	public EIACostGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		
		Label title = new Label("<h3>Costos/Depreciación</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
	
		Label titleDep = new Label("<h3>Registro de Depreciación</h3>");
		titleDep.setHeight(35);
		titleDep.setWidth100();
		titleDep.setStyleName("title-label");
		
		// //////Botones laterales
        VLayout sideButtons = GHAUiHelper.createBar(
	    		new GHAImgButton("../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
					}
				}),
	    		new GHAImgButton("../resources/icons/edit.png"),
	    		new GHAImgButton("../resources/icons/delete.png"),
	    		new GHAImgButton("../resources/icons/set.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
					}
				}));
		
        HLayout mainPanel = new HLayout();
        mainPanel.addMembers(eiaDeprecationGrid,sideButtons);
        
	    addMembers(title,new LayoutSpacer(), GHAUiHelper.verticalGraySeparator("10px"),titleDep,mainPanel);
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
