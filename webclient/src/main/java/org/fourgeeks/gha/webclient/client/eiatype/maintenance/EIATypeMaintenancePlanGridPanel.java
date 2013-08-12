package org.fourgeeks.gha.webclient.client.eiatype.maintenance;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeMaintenancePlanGridPanel extends VLayout implements EIATypeSelectionListener,GHAClosable,GHAHideable{

	private EIATypeMaintenancePlanGrid eiaTypeMaintenancePlanGrid = new EIATypeMaintenancePlanGrid();
	private EIATypeMainenanceProtocolForm mainenanceProtocolForm = new EIATypeMainenanceProtocolForm();
	
	public EIATypeMaintenancePlanGridPanel() {
		setWidth100();
		
// //////Botones laterales
		
	    VLayout sideButtons = GHAUiHelper.createBar(
	    		new GHAImgButton("../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						mainenanceProtocolForm.animateShow(AnimationEffect.FLY);
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
		mainPanel.addMembers(eiaTypeMaintenancePlanGrid, sideButtons);
	    
		addMembers(mainPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		mainenanceProtocolForm.close();
	}
	
	@Override
	public void hide() {
		mainenanceProtocolForm.hide();
	}

}
