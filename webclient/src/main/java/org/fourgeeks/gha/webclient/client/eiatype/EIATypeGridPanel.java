package org.fourgeeks.gha.webclient.client.eiatype;

import com.google.gwt.user.client.Event;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeGridPanel extends HLayout {

	private EIATypeForm form;

	public EIATypeGridPanel() {
		super();
		form = new EIATypeForm();
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		setWidth100();
		setHeight("320px");
		// setBackgroundImage("../resources/img/tab1.jpg");
		setBackgroundColor("#E0E0E0");

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
		addButton.sinkEvents(Event.MOUSEEVENTS);
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
		addMembers(new EIATypeGrid(), sideButtons);

	}
}
