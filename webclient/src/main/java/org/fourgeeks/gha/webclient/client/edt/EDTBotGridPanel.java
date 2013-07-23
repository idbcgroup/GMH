package org.fourgeeks.gha.webclient.client.edt;

import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EDTBotGridPanel extends VLayout implements GHAClosable{

	private EDTBotGrid botGrid;
	{
		botGrid = new EDTBotGrid();
	}

	public EDTBotGridPanel() {
		super();
		setStyleName("sides-padding");// Esto es VUDU!
		setWidth100();
		
		setBackgroundColor("#E0E0E0");
		
		Label botTitle = new Label("<h3>Atendidos</h3>");
		botTitle.setHeight(35);
		botTitle.setWidth100();
		botTitle.setStyleName("title-label");
		
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
				GHAUiHelper.verticalGraySeparator("3px"),
				new GHAImgButton("../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						//TODO: Funcion que agregue una actividad
					}
				}));

		HLayout mainpanel = new HLayout();
		mainpanel.addMembers(botGrid, sideButtons);
		
		addMembers(botTitle,mainpanel);
	}

	@Override
	public void close() {
		//TODO:Close the add forms
	}
}
