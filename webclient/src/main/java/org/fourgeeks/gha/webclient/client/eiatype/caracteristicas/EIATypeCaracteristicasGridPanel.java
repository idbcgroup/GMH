package org.fourgeeks.gha.webclient.client.eiatype.caracteristicas;

import org.fourgeeks.gha.webclient.client.UI.GHATextItem;

import com.google.gwt.user.client.Event;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeCaracteristicasGridPanel extends VLayout {

	public EIATypeCaracteristicasGridPanel() {
		// setShowEdges(true);
//		setStyleName("exampleTitle");
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		setVisibility(Visibility.HIDDEN);
		setAlign(Alignment.CENTER);
		
		Label title = new Label("<h3>Caracteristicas del EIA Type</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);

		GHATextItem codigoEIA = new GHATextItem("Código");

		GHATextItem nombreEIA = new GHATextItem("Nombre");

		GHATextItem marcaEIA = new GHATextItem("Marca");

		GHATextItem modeloEIA = new GHATextItem("Modelo");

		GHATextItem fabricante = new GHATextItem("Fabricante");

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);
		form.setItems(codigoEIA, nombreEIA, marcaEIA, modeloEIA, fabricante);

		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		// botones1.setBackgroundImage("../resources/img/botonBox.jpg");
		sideButtons.setBackgroundColor("#E0E0E0");
		sideButtons.setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);

		ImgButton addButton = new ImgButton();
		addButton.sinkEvents(Event.MOUSEEVENTS);
		addButton.setSrc("../resources/icons/new.png");
		addButton.setShowRollOver(false);

		addButton.setSize("20px", "20px");
		Img editButton = new Img("../resources/icons/edit.png");
		editButton.setSize("20px", "20px");

		ImgButton cancelButton = new ImgButton();
		cancelButton.setSrc("../resources/icons/delete.png");
		cancelButton.setSize("20px", "20px");
		cancelButton.setShowRollOver(false);
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
			
			}
		});

		sideButtons.addMembers(addButton, editButton, cancelButton);

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, sideButtons);
		
		addMember(gridPanel);
	}
}
