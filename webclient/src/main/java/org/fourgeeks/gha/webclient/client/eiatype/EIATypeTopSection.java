package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.GHAImg;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHATabSet;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.History;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeTopSection extends HLayout implements
		EIATypeSelectionListener, ResizeHandler {

	private final EIATypeTab _eiaTypeTab;
	private List<EIATypeSelectionListener> selectionListeners;
	private EIATypeSearchForm eiaTypeSearchForm;
	private GHATextItem nameItem, codeItem, brandItem, modelItem, manItem,
			descriptionItem, useDescriptionItem, umdnsCodeItem, mobilityItem,
			typeItem, subTypeItem, isServiceItem;
	{
		selectionListeners = new LinkedList<EIATypeSelectionListener>();
		eiaTypeSearchForm = new EIATypeSearchForm();
		codeItem = new GHATextItem("Código", false);
		nameItem = new GHATextItem("Nombre", false);
		brandItem = new GHATextItem("Marca", false);
		modelItem = new GHATextItem("Modelo", false);
		manItem = new GHATextItem("Fabricante", false);
		descriptionItem = new GHATextItem("Descripción", false);
		descriptionItem.setWidth(210);
		descriptionItem.setColSpan(2);
		useDescriptionItem = new GHATextItem("Uso", false);
		useDescriptionItem.setWidth(210);
		useDescriptionItem.setColSpan(2);
		umdnsCodeItem = new GHATextItem("EIAUMDNS", false);
		mobilityItem = new GHATextItem("Movilidad", false);
		typeItem = new GHATextItem("Tipo de Equipo", false);
		subTypeItem = new GHATextItem("Subtipo", false);
		isServiceItem = new GHATextItem("Es servicio", false);
	}

	public EIATypeTopSection(EIATypeTab eiaTypeTab) {
		super();
		_eiaTypeTab = eiaTypeTab;
		
		GHAUiHelper.addResizeHandler(this);
		eiaTypeSearchForm.AddEIATypeSelectionListener(eiaTypeTab);

		eiaTypeTab.addHideableHandler(new GHAHideable() {
			@Override
			public void hide() {
				eiaTypeSearchForm.hide();
			}
		});
		eiaTypeTab.addClosableHandler(new GHAClosable() {

			@Override
			public void close() {
				eiaTypeSearchForm.destroy();
			}
		});

		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		setBackgroundColor("#EAEAEA");
		setDefaultLayoutAlign(VerticalAlignment.CENTER);

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);
		form.setItems(codeItem, nameItem, descriptionItem, brandItem,
				modelItem, manItem, useDescriptionItem, umdnsCodeItem,
				mobilityItem, typeItem, subTypeItem, isServiceItem);

		// Panel de la Fotografia

		HLayout photoPanel = new HLayout();
		photoPanel.setMembersMargin(10);
		photoPanel.setWidth(130);
		// photoPanel.setDefaultLayoutAlign(Alignment.CENTER);
		GHAImg photo = new GHAImg("../resources/img/Foto.jpg", 80, 80);
		// photo.setTop(8);
		photo.setStyleName("top-8");

		VLayout photoBotones = new VLayout();
		photoBotones.setWidth(30);
		photoBotones.setLayoutMargin(5);
		photoBotones.setMembersMargin(10);
		photoBotones.setDefaultLayoutAlign(Alignment.CENTER);

		GHAImgButton searchPhoto = new GHAImgButton("../resources/icons/search.png");
		// GHAButton cleanPhoto = new GHAButton("../resources/icons/clean.png");
		photoBotones.addMembers(searchPhoto);

		photoPanel.addMembers(photo, photoBotones);

		// Botones laterales del Panel

		VLayout panelBotones = new VLayout();
		panelBotones.setWidth(30);
		panelBotones.setLayoutMargin(5);
		panelBotones.setMembersMargin(10);
		panelBotones.setDefaultLayoutAlign(Alignment.CENTER);

		GHAImgButton cleanImg = new GHAImgButton("../resources/icons/clean.png");
		cleanImg.setSize("20px", "20px");
		cleanImg.setHoverStyle("boxed");
		GHAImgButton searchImg = new GHAImgButton("../resources/icons/search.png");
		searchImg.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});
		GHAImgButton cancelButton = new GHAImgButton("../resources/icons/cancel.png");
		cancelButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				_eiaTypeTab.close();
				
			}
		});
		
		panelBotones.addMembers(searchImg, cleanImg, cancelButton);

		addMembers(form, photoPanel, new LayoutSpacer(), panelBotones);
	}

	@Override
	public void select(EiaType eiaType) {
		// Window.alert("top section select");
		selectEiaType(eiaType);
		codeItem.setValue(eiaType.getCode());
		nameItem.setValue(eiaType.getName());

		if (eiaType.getBrand() != null)
			brandItem.setValue(eiaType.getBrand().getName());

		modelItem.setValue(eiaType.getModel());

		if (eiaType.getManufacturer() != null)
			manItem.setValue(eiaType.getManufacturer().getName());

		descriptionItem.setValue(eiaType.getDescription());
		useDescriptionItem.setValue(eiaType.getUseDescription());
		umdnsCodeItem.setValue(eiaType.getEiaUmdns());

		if (eiaType.getMobility() != null)
			mobilityItem.setValue(eiaType.getMobility().toString());

		if (eiaType.getType() != null)
			typeItem.setValue(eiaType.getType().toString());

		if (eiaType.getSubtype() != null)
			subTypeItem.setValue(eiaType.getSubtype().toString());

	}

	public void search() {
		eiaTypeSearchForm.open();
	}

	public void AddEIATypeSelectionListener(
			EIATypeSelectionListener selecionListener) {
		selectionListeners.add(selecionListener);
	}

	private void selectEiaType(EiaType eiaType) {
		for (EIATypeSelectionListener listener : selectionListeners)
			listener.select(eiaType);

	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
	}
	
}
