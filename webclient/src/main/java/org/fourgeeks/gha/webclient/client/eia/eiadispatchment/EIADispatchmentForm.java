package org.fourgeeks.gha.webclient.client.eia.eiadispatchment;

import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
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
		super();
		setTop(110);
		setHeight(GHAUiHelper.getTabHeight());
		setStyleName("sides-padding top-padding box");
		
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
		topPanel.setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT);
//		topPanel.setStyleName("sides-padding top-padding");
		
		topPanel.addMembers(new LayoutSpacer(), sideButtons);
		
		Label title = new Label("<h3>Acta de Instalación</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
		
		HLayout botPanel = new HLayout();
		botPanel.addMembers(dispatchmentGrid);
		
		addMembers(topPanel,GHAUiHelper.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT+"px"),title, botPanel);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight());
	}

}
