package org.fourgeeks.gha.webclient.client.eia;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATopSection extends HLayout implements EIASelectionListener, GHAClosable, ResizeHandler {

	private List<EIASelectionListener> selectionListeners;
	private GHATextItem acceptationDate, actualCost, adquisitionCost, 
	responsibleRole, code, 	eiaType, fixedAssetIdentifier,
	installationDate, buildingLocation,	obu, purchaseDate, serialNumber, state, 
	itType, realWarrantySince, realWarrantyTime, intWarrantySince, intWarrantyTime;
	
	{
		
		acceptationDate = new GHATextItem("Recibido", false);
		actualCost = new GHATextItem("Costo actual", false);
		adquisitionCost = new GHATextItem("Costo", false);
		responsibleRole = new GHATextItem("Responsable", false);
		code = new GHATextItem("Código", false);
		eiaType = new GHATextItem("Tipo de equipo", false);
		fixedAssetIdentifier = new GHATextItem("Identificador", false);
		installationDate = new GHATextItem("Instalación", false);
		
		buildingLocation = new GHATextItem("Ubicación", false);
		
		obu = new GHATextItem("Organización", false);
		purchaseDate = new GHATextItem("Fecha de compra", false);
		serialNumber = new GHATextItem("Serial", false);
		state = new GHATextItem("Estado", false);
		itType = new GHATextItem("Tipo it", false);
		
		realWarrantySince = new GHATextItem("Garantía desde", false);
		realWarrantyTime = new GHATextItem("Período de garantía", false);
		
		intWarrantySince = new GHATextItem("Garantía intermedia desde", false);
		intWarrantyTime = new GHATextItem("Período de garantía intermedia desde", false);
		
	}

	public EIATopSection(EIATab eiaTab) {
		super();
		eiaTab.addClosableHandler(this);
		GHAUiHelper.addResizeHandler(this);
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		setDefaultLayoutAlign(VerticalAlignment.CENTER);
		setBackgroundColor("#EAEAEA");

		DynamicForm form = new DynamicForm();
		//form.setWidth("100px");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(9);
		form.setItems(
				acceptationDate, actualCost, adquisitionCost, 
				responsibleRole, code, eiaType, fixedAssetIdentifier,
				installationDate, buildingLocation, 
				obu, purchaseDate, serialNumber, state, 
				itType, realWarrantySince, realWarrantyTime, intWarrantySince, 
				intWarrantyTime
		);

		GHAImgButton cleanImg = new GHAImgButton("../resources/icons/clean.png");
		GHAImgButton canelButton = new GHAImgButton("../resources/icons/cancel.png");
		GHAImgButton searchImg = new GHAImgButton("../resources/icons/search.png");
		searchImg.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});

		VLayout panelBotones = new VLayout();
		panelBotones.setWidth(30);
		panelBotones.setLayoutMargin(5);
		panelBotones.setMembersMargin(10);
		panelBotones.setDefaultLayoutAlign(Alignment.CENTER);
		panelBotones.addMembers(searchImg, cleanImg, canelButton);

		addMembers(form, new LayoutSpacer(), panelBotones);

	}

	public void search() {
	}



	public void AddEIATypeSelectionListener(EIATypeSelectionListener selecionListener) {
		//selectionListeners.add(selecionListener);
	}

	@Override
	public void close() {
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");		
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub
		for (EIASelectionListener listener : selectionListeners)
			listener.select(eia);

		
	}
}