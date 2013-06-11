package org.fourgeeks.gha.webclient.client.eia.caracteristicas;

import org.fourgeeks.gha.webclient.client.eia.EIAForm;
import org.fourgeeks.gha.webclient.client.eia.EIARecord;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAGridPanel extends VLayout {

	private EIAForm form;
	private EIAGrid eiaTypeGrid = new EIAGrid();

	public EIAGridPanel() {
		super();
		form = new EIAForm();
		
		setWidth100();
		setBackgroundColor("#E0E0E0");
		//setStyleName("sides-padding top-padding");// Esto es VUDU!
		setWidth100();
		// setBackgroundImage("../resources/img/tab1.jpg");
		setBackgroundColor("#E0E0E0");

		
		HLayout filterPanel = new HLayout();
		filterPanel.setHeight(30);
		filterPanel.setWidth100();
//		filterPanel.setMembersMargin(15);
//		FilterBuilder filterBuilder = new FilterBuilder();
//		IButton filterButton = new IButton("Filtrar");
		
//		filterPanel.addMembers(filterBuilder,filterButton);
		
		HLayout mainPanel = new HLayout();

		// //////Botones laterales
		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		// botones1.setBackgroundImage("../resources/img/botonBox.jpg");
		sideButtons.setBackgroundColor("#E0E0E0");
		sideButtons.setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);

		ImgButton addButton = new ImgButton();
		// addButton.sinkEvents(Event.MOUSEEVENTS);
		addButton.setSrc("../resources/icons/new.png");
		addButton.setShowRollOver(false);
		addButton.setSize("20px", "20px");
		addButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				form.animateShow(AnimationEffect.FLY);
			}
		});
		Img editButton = new Img("../resources/icons/edit.png");
		editButton.setSize("20px", "20px");
		Img deleteButton = new Img("../resources/icons/delete.png");
		deleteButton.setSize("20px", "20px");

		ImgButton setsButton = new ImgButton();
		setsButton.sinkEvents(Event.MOUSEEVENTS);
		setsButton.setSrc("../resources/icons/set.png");
		setsButton.setShowRollOver(false);
		setsButton.setSize("20px", "20px");
		setsButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				EIARecord selectedRecord = (EIARecord) eiaTypeGrid
						.getSelectedRecord();
				History.newItem("eia/" + selectedRecord.getCode());
			}
		});

		sideButtons.addMembers(addButton, editButton, deleteButton, setsButton);
		
		mainPanel.addMembers(eiaTypeGrid, sideButtons);
		
		addMembers(filterPanel,mainPanel);

	}
}