package org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.protocol.MaintenanceProtocolForm;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanGrid;

import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeMaintenancePlanGridPanel extends VLayout implements EIATypeSelectionListener,GHAClosable,GHAHideable{

	private MaintenancePlanGrid eiaTypeMaintenancePlanGrid = new MaintenancePlanGrid();
	private MaintenanceProtocolForm mainenanceProtocolForm = new MaintenanceProtocolForm();
	
	public EIATypeMaintenancePlanGridPanel() {
		setWidth100();
		
// //////Botones laterales
		
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
						mainenanceProtocolForm.animateShow(AnimationEffect.FLY);
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
