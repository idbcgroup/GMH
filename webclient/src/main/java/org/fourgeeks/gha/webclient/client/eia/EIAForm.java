package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAForm extends HLayout {

	public EIAForm() {
		// setShowEdges(true);
		setStyleName("exampleTitle");
		setPadding(10);
		setWidth100();
		setTop(197);
		setLeft(0);
		setHeight("75%");
		setBackgroundColor("#E0E0E0");
		setVisibility(Visibility.HIDDEN);
		setAlign(Alignment.CENTER);
		setAnimateTime(800);
		GHATextItem codigoEIA = new GHATextItem("Código");

		GHATextItem nombreEIA = new GHATextItem("Nombre");

		GHATextItem marcaEIA = new GHATextItem("Marca");

		GHATextItem modeloEIA = new GHATextItem("Modelo");

		GHATextItem fabricante = new GHATextItem("Fabricante");

		DynamicForm form = new DynamicForm();
		form.setWidth("*");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(1);
		form.setItems(codigoEIA, nombreEIA, marcaEIA, modeloEIA, fabricante);

		VLayout sideButtons = GHAUiHelper.createBar(
				new GHAImgButton("../resources/icons/new.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						//TODO

					}
				}),
				new GHAImgButton("../resources/icons/edit.png"), 
				new GHAImgButton("../resources/icons/delete.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						EIAForm.this.animateHide(AnimationEffect.FLY);
					}
				}));

		addMembers(form, sideButtons);	
	}
}
