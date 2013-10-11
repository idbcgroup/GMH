package org.fourgeeks.gha.webclient.client.eia.eiadispatchment;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.eia.EIAGrid;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIADispatchmentForm extends GHASlideInWindow {

	private EIAGrid dispatchmentGrid;
	{
		dispatchmentGrid = new EIAGrid();
	}
	
	
	public EIADispatchmentForm() {
		super(1);
		setTop(GHAUiHelper.getTopSpace());
		setHeight(GHAUiHelper.getTabHeight()-4);
		
////////Botones laterales
		VLayout sideButtons = GHAUiHelper.createBar(
	    		new GHAImgButton("../resources/icons/check.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
					}
				}),
				GHAUiHelper.verticalGraySeparator("2px"),
	    		new GHAImgButton("../resources/icons/cancel.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						hide();
					}
				}));
		
		HLayout topPanel = new HLayout();
		topPanel.setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT -30);
		topPanel.setStyleName("sides-padding");
		
		topPanel.addMembers(new LayoutSpacer(), sideButtons);
		
		Label title = new Label("<h3>Despacho de equipos</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
		
		HLayout botPanel = new HLayout();
		botPanel.setStyleName("sides-padding padding-top");
		botPanel.addMembers(dispatchmentGrid);
		
		addMembers(title,topPanel,GHAUiHelper.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT+"px"), botPanel);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight()-4);
	}

}
