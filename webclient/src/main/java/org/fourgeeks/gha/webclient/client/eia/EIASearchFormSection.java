package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHATextItem;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIASearchFormSection extends HLayout {

	private EIAGrid eiaGrid;
	private GHATextItem nameField;

	public EIASearchFormSection(EIAGrid eiaTypeGrid) {
		super();
		this.eiaGrid = eiaTypeGrid;
		setStyleName("sides-padding");// Esto es VUDU!
		setWidth100();
		setHeight("68px");
		// setBackgroundImage("../resources/img/tab1.jpg");
		setBackgroundColor("#E0E0E0");
		// setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);

		GHATextItem codigoEIA = new GHATextItem("Código");
		nameField = new GHATextItem("Nombre");
		GHATextItem marcaEIA = new GHATextItem("Marca");
		GHATextItem modeloEIA = new GHATextItem("Modelo");
		GHATextItem fabricante = new GHATextItem("Fabricante");

		DynamicForm form = new DynamicForm();
		form.setWidth("*");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(10);
		form.setItems(codigoEIA, nameField, marcaEIA, modeloEIA, fabricante);
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
		ImgButton searchImg = new ImgButton();
		searchImg.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// search();

			}
		});
		searchImg.setSrc("../resources/icons/search.png");
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

		// ImgButton helpImg = new ImgButton();
		// helpImg.setSrc("../resources/icons/boton6.png");
		// helpImg.setSize("20px", "20px");
		// Img reloadImg = new Img("../resources/icons/boton7.png");
		// reloadImg.setSize("20px", "20px");
		// botones2.addMembers(helpImg, reloadImg);

		// Agregando los 3 layouts al principal layout de arriba

		addMembers(form, panelBotones);
	}

	// private void search() {
	// EiaType eiaType = new EiaType();
	// eiaType.setName(nameField.getValueAsString());
	// EIAModel.find(eiaType, new GHAAsyncCallback<List<EiaType>>() {
	//
	// @Override
	// public void onSuccess(List<EiaType> eiaTypes) {
	// ListGridRecord[] array = (ListGridRecord[]) EIAUtil
	// .toGridRecords(eiaTypes).toArray(new EIARecord[] {});
	// eiaTypeGrid.setData(array);
	// }
	//
	// });
	// }

}
