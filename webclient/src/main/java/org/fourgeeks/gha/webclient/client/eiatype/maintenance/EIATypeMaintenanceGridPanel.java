package org.fourgeeks.gha.webclient.client.eiatype.maintenance;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHASectionForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.user.client.Event;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeMaintenanceGridPanel extends VLayout implements
		EIATypeSelectionListener {

	private EIATypeMaintenancePlanGrid maintenancePlanGrid = new EIATypeMaintenancePlanGrid();
	private EIATypeMaintenanceProtocolGrid maintenanceProtocolGrid = new EIATypeMaintenanceProtocolGrid();
	private GHASectionForm sectionForm;

	public EIATypeMaintenanceGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!

		Label title = new Label("<h3>Planes y Protocolos de Mantenimiento</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");

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
				// TODO Auto-generated method stub

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
				// EIARecord selectedRecord = (EIARecord)
				// eiaTypeGrid.getSelectedRecord();
				// History.newItem("eia/" + selectedRecord.getCode());
			}
		});

		sideButtons.addMembers(addButton, editButton, deleteButton, setsButton);

		final HLayout planMantLayout = new HLayout();
		planMantLayout.setWidth100();
		planMantLayout.addMembers(maintenancePlanGrid, sideButtons);

		VLayout sideButtons2 = new VLayout();
		sideButtons2.setWidth(30);
		sideButtons2.setLayoutMargin(5);
		// botones1.setBackgroundImage("../resources/img/botonBox.jpg");
		sideButtons2.setBackgroundColor("#E0E0E0");
		sideButtons2.setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);
		sideButtons2.setMembersMargin(10);
		sideButtons2.setDefaultLayoutAlign(Alignment.CENTER);

		ImgButton addButton2 = new ImgButton();
		// addButton.sinkEvents(Event.MOUSEEVENTS);
		addButton2.setSrc("../resources/icons/new.png");
		addButton2.setShowRollOver(false);
		addButton2.setSize("20px", "20px");

		addButton2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		});

		Img editButton2 = new Img("../resources/icons/edit.png");
		editButton2.setSize("20px", "20px");
		Img deleteButton2 = new Img("../resources/icons/delete.png");
		deleteButton2.setSize("20px", "20px");

		ImgButton setsButton2 = new ImgButton();
		setsButton2.sinkEvents(Event.MOUSEEVENTS);
		setsButton2.setSrc("../resources/icons/set.png");
		setsButton2.setShowRollOver(false);
		setsButton2.setSize("20px", "20px");
		setsButton2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// EIARecord selectedRecord = (EIARecord)
				// eiaTypeGrid.getSelectedRecord();
				// History.newItem("eia/" + selectedRecord.getCode());
			}
		});

		sideButtons2.addMembers(addButton2, editButton2, deleteButton2,
				setsButton2);

		final HLayout protMantLayout = new HLayout();
		protMantLayout.addMembers(maintenanceProtocolGrid, sideButtons2);
		protMantLayout.setWidth100();
		sectionForm = new GHASectionForm();
		sectionForm.addSection("Planes Mantenimiento", planMantLayout, true);
		sectionForm.addSection("Protocolos Mantenimiento", protMantLayout,
				false);

		addMembers(title, sectionForm);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		super.hide();
		sectionForm.deactivate();

	}

	public void hideSections() {
		sectionForm.deactivate();
	}
}
