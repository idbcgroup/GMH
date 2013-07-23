package org.fourgeeks.gha.webclient.client.edt;

import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EDTTopGridPanel extends VLayout implements GHAClosable{

	private EDTTopGrid topGrid;
	{
		topGrid = new EDTTopGrid();
	}

	public EDTTopGridPanel() {
		super();
		setWidth100();
		setStyleName("sides-padding");// Esto es VUDU!
		
		setBackgroundColor("#E0E0E0");
		
		//Title
		Label topTitle = new Label("<h3>Por Atender</h3>");
		topTitle.setHeight(35);
		topTitle.setWidth100();
		topTitle.setStyleName("title-label");
		
		// //////Botones laterales
		
		VLayout sideButtons = GHAUiHelper.createBar(
				new GHAImgButton("../resources/icons/save.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						//TODO: Funcion que guarde
					}
				}),
				new GHAImgButton("../resources/icons/undo.png",new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						
					}
				}),
				GHAUiHelper.verticalGraySeparator("2px"),
				new GHAImgButton("../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						//TODO: Funcion que agregue una actividad
					}
				}));

		HLayout mainpanel = new HLayout();
		mainpanel.addMembers(topGrid, sideButtons);
		
		addMembers(topTitle,mainpanel);
	}

	@Override
	public void close() {
		//TODO:Close the add forms
	}
}
