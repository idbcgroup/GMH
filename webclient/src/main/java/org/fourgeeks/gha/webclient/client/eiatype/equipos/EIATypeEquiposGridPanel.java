package org.fourgeeks.gha.webclient.client.eiatype.equipos;

import org.fourgeeks.gha.webclient.client.eiatype.EIATypeRecord;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeEquiposGridPanel extends VLayout {

	private EIATypeEquiposGrid eiaTypeEquiposGrid = new EIATypeEquiposGrid();

	public EIATypeEquiposGridPanel() {
		super();
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		setWidth100();
		setBackgroundColor("#E0E0E0");
		
		Label title = new Label("<h3>Equipos pertenecientes al EIA Type</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);

		// //////Botones laterales
		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		// botones1.setBackgroundImage("../resources/img/botonBox.jpg");
		sideButtons.setBackgroundColor("#E0E0E0");
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
				EIATypeRecord selectedRecord = (EIATypeRecord) eiaTypeEquiposGrid
						.getSelectedRecord();
				History.newItem("eia/" + selectedRecord.getCode());
			}
		});

		sideButtons.addMembers(addButton, editButton, deleteButton, setsButton);
		
		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(eiaTypeEquiposGrid, sideButtons);
		addMember(mainLayout);

	}

	/**
	 * @return the eiaTypeGrid
	 */
	public EIATypeEquiposGrid getEiaTypeGrid() {
		return eiaTypeEquiposGrid;
	}
}
