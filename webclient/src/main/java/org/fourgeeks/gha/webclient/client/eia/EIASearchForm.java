package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHATextItem;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PickerIcon;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIASearchForm extends HLayout {

	public EIASearchForm() {
		super();
		setHeight("68px");
		setWidth100();
		// setStylePrimaryName("eia-search-form");
		// setStyleName("eia-search-form2");
		setWidth100();
		setHeight("68px");
		setBackgroundImage("../resources/img/tab1.jpg");
		setBackgroundColor("#E0E0E0");
		setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);

		GHATextItem codigoEIA = new GHATextItem("Código");
		// codigoEIA.setRequired(true);

		PickerIcon searchPicker = new PickerIcon(PickerIcon.SEARCH,
				new FormItemClickHandler() {
					@Override
					public void onFormItemClick(FormItemIconClickEvent event) {
						// TODO Auto-generated method stub
						SC.say("Search icon clicked");
					}
				});
		searchPicker.setSrc("../resources/icons/boton4.png");
		searchPicker.setWidth(18);
		searchPicker.setHeight(18);

		TextItem nombreEIA = new TextItem();
		nombreEIA.setTitle("Nombre EIA");
		nombreEIA.setIcons(searchPicker);
		// nombreEIA.setRequired(true);

		TextItem marcaEIA = new TextItem();
		marcaEIA.setTitle("Marca EIA");
		marcaEIA.setIcons(searchPicker);
		// marcaEIA.setRequired(true);

		TextItem modeloEIA = new TextItem();
		modeloEIA.setTitle("Modelo EIA");
		// modeloEIA.setRequired(true);

		TextItem fabricante = new TextItem();
		fabricante.setTitle("Fabricante");
		// fabricante.setRequired(true);

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);
		form.setItems(codigoEIA, nombreEIA, marcaEIA, modeloEIA, fabricante);
		form.setCellPadding(10);
		form.setPadding(13);

		VLayout botones1 = new VLayout();
		botones1.setHeight("68px");
		botones1.setWidth("4%");
		botones1.setLayoutMargin(5);
		botones1.setBackgroundImage("../resources/img/botonBox.jpg");
		botones1.setBackgroundColor("#E0E0E0");
		botones1.setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);
		botones1.setMembersMargin(10);
		botones1.setDefaultLayoutAlign(Alignment.CENTER);

		Img cleanImg = new Img("../resources/icons/boton3.png");
		cleanImg.setSize("20px", "20px");
		Img searchImg = new Img("../resources/icons/boton4.png");
		searchImg.setSize("20px", "20px");
		botones1.addMembers(cleanImg, searchImg);

		VLayout botones2 = new VLayout();
		botones2.setHeight("68px");
		botones2.setWidth("*");
		botones2.setLayoutMargin(5);
		botones2.setBackgroundImage("../resources/img/botonBox.jpg");
		botones2.setBackgroundColor("#E0E0E0");
		botones2.setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);
		botones2.setMembersMargin(10);
		botones2.setDefaultLayoutAlign(Alignment.CENTER);

		Img helpImg = new Img("../resources/icons/boton6.png");
		helpImg.setSize("20px", "20px");
		Img reloadImg = new Img("../resources/icons/boton7.png");
		reloadImg.setSize("20px", "20px");
		botones2.addMembers(helpImg, reloadImg);

		// Agregando los 3 layouts al principal layout de arriba

		addMembers(form, botones1, botones2);
	}

}
