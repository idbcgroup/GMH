package org.fourgeeks.gha.webclient.client.eia.caracteristicas;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAEquiposGridPanel extends VLayout {

	private EIAEquiposCreateForm form;
	private EIAEquiposGrid eiaGrid;
	{
		eiaGrid = new EIAEquiposGrid();
	}

	public EIAEquiposGridPanel() {
		super();
		form = new EIAEquiposCreateForm();
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		Label title = new Label("<h3>Lista de Equipos por Tipo EIA</h3>");
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

		sideButtons.addMembers(addButton, editButton, deleteButton);

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(eiaGrid, sideButtons);

		addMembers(gridPanel);

	}

	public void setData(ListGridRecord[] array) {
		eiaGrid.setData(array);
	}
}