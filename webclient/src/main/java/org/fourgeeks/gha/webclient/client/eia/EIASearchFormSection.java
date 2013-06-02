package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHATextItem;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIASearchFormSection extends HLayout {

	public EIASearchFormSection() {
		super();
		setStyleName("sides-padding");// Esto es VUDU!
		setWidth100();
		setHeight("68px");
		// setBackgroundImage("../resources/img/tab1.jpg");
		setBackgroundColor("#E0E0E0");
		// setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);

		GHATextItem codigoEIA = new GHATextItem("Código");
		// codigoEIA.setRequired(true);

		// PickerIcon searchPicker = new PickerIcon(PickerIcon.SEARCH,
		// new FormItemClickHandler() {
		// @Override
		// public void onFormItemClick(FormItemIconClickEvent event) {
		// // TODO Auto-generated method stub
		// SC.say("Search icon clicked");
		// }
		// });
		// searchPicker.setSrc("../resources/icons/boton4.png");
		// searchPicker.setWidth(18);
		// searchPicker.setHeight(18);

		GHATextItem nombreEIA = new GHATextItem("Nombre");
		// nombreEIA.setIcons(searchPicker);
		// nombreEIA.setRequired(true);

		GHATextItem marcaEIA = new GHATextItem("Marca");
		// marcaEIA.setIcons(searchPicker);
		// marcaEIA.setRequired(true);

		GHATextItem modeloEIA = new GHATextItem("Modelo");
		// modeloEIA.setRequired(true);

		GHATextItem fabricante = new GHATextItem("Fabricante");
		// fabricante.setRequired(true);

		DynamicForm form = new DynamicForm();
		form.setWidth("*");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(10);
		form.setItems(codigoEIA, nombreEIA, marcaEIA, modeloEIA, fabricante);
		// form.setCellPadding(20);
		// form.setPadding(10);

		VLayout panelBotones = new VLayout();
		panelBotones.setHeight("68px");
		panelBotones.setWidth(30);
		panelBotones.setLayoutMargin(5);
		// botones1.setBackgroundImage("../resources/img/botonBox.jpg");
		panelBotones.setBackgroundColor("#E0E0E0");
		panelBotones.setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);
		panelBotones.setMembersMargin(10);
		panelBotones.setDefaultLayoutAlign(Alignment.CENTER);
		Img cleanImg = new Img("../resources/icons/boton3.png");
		cleanImg.setSize("20px", "20px");
		cleanImg.setHoverStyle("boxed");
		Img searchImg = new Img("../resources/icons/boton4.png");
		searchImg.setHoverStyle("boxed");
		searchImg.setCanHover(true);
		searchImg.setShowHover(true);
		searchImg.setSize("20px", "20px");
		panelBotones.addMembers(searchImg, cleanImg);

		VLayout botones2 = new VLayout();
		botones2.setHeight("68px");
		botones2.setWidth(20);
		botones2.setLayoutMargin(5);
		// botones2.setBackgroundImage("../resources/img/botonBox.jpg");
		botones2.setBackgroundColor("#E0E0E0");
		botones2.setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);
		botones2.setMembersMargin(10);
		botones2.setDefaultLayoutAlign(Alignment.CENTER);

		Img helpImg = new Img("../resources/icons/boton6.png");
		helpImg.setSize("20px", "20px");
		// Img reloadImg = new Img("../resources/icons/boton7.png");
		// reloadImg.setSize("20px", "20px");
		// botones2.addMembers(helpImg, reloadImg);

		// Agregando los 3 layouts al principal layout de arriba

		addMembers(form, panelBotones);
	}

}
