package org.fourgeeks.gha.webclient.client.eia;

import java.util.LinkedList;
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
	private GHATextItem acceptationDate, actualCost, actualCostCurrency, adquisitionCost, adquisitionCostCurrency,
	adquisitionCostCurrencyLocal, adquisitionCostLocal, responsibleRole, code, contabilizationDate, dateLastDepreciation,
	depreciationMethod, depreciationTime, depreciationTimePoT, desincorporatedDate, desincorporateReason, eiaType, fixedAssetIdentifier,
	installationDate, installationProvider, lifeTime, lifeTimePoT, buildingLocation, attendedLocation, maintenanceLocation, maintenanceProvider,
	obu, purchaseDate, purchaseInvoiceDate, purchaseInvoiceNumber, purchaseOrderDate, purchaseOrderNumber, receptionDate, serialNumber, state, 
	provider, itType, machineName, ipAddress, macAddress, realWarrantySince, realWarrantyTime, realWarrantyPoT, realWarrantyBegin, intWarrantySince, 
	intWarrantyTime, intWarrantyPoT, intWarrantyBegin;
	
	{
		selectionListeners = new LinkedList<EIASelectionListener>();
		acceptationDate = new GHATextItem("Fecha de Instalación", false);
		actualCost = new GHATextItem("Costo actual", false);
		actualCostCurrency = new GHATextItem("Costo actual moneda indicada", false);
		adquisitionCost = new GHATextItem("Costo de adquisición", false);
		adquisitionCostCurrency = new GHATextItem("Costo de adquisición moneda indicada", false);
		adquisitionCostCurrencyLocal = new GHATextItem("Costo de adquisición moneda local", false);
		adquisitionCostLocal = new GHATextItem("--", false);
		responsibleRole = new GHATextItem("Responsable", false);
		code = new GHATextItem("Código del equipo", false);
		contabilizationDate = new GHATextItem("Fecha de Contabilización", false);
		dateLastDepreciation = new GHATextItem("Fecha última depresiación", false);
		
		depreciationMethod = new GHATextItem("Método de depresiación", false);
		depreciationTime = new GHATextItem("Período de depresiación", false);
		depreciationTimePoT = new GHATextItem("Período de depresiación PoT", false);
		desincorporatedDate = new GHATextItem("Fecha de desincorporación", false);
		desincorporateReason = new GHATextItem("Razón de desincorporación", false);
		eiaType = new GHATextItem("Tipo de equipo", false);
		fixedAssetIdentifier = new GHATextItem("Identificador de activo fijo", false);
		installationDate = new GHATextItem("Fecha de instalación", false);
		installationProvider = new GHATextItem("Proveedor de instalación", false);
		lifeTime = new GHATextItem("Tiempo de vida", false);
		lifeTimePoT = new GHATextItem("Tiempo de vida PoT", false);
		buildingLocation = new GHATextItem("Ubicación de construccion", false);
		attendedLocation = new GHATextItem("Ubicación de atención", false);
		maintenanceLocation = new GHATextItem("Ubicación de mantenimiento", false);
		maintenanceProvider = new GHATextItem("Proveedor de mantenimiento", false);
		
		obu = new GHATextItem("obu", false);
		
		purchaseDate = new GHATextItem("Fecha de compra", false);
		purchaseInvoiceDate = new GHATextItem("Fecha de compra de la factura", false);
		purchaseInvoiceNumber = new GHATextItem("Número de compra de la factura", false);
		purchaseOrderDate = new GHATextItem("Fecha de la orden de compra", false);
		purchaseOrderNumber = new GHATextItem("Número de la orden de compra", false);
		receptionDate = new GHATextItem("Fecha de recepción", false);
		serialNumber = new GHATextItem("Número de serial", false);
		state = new GHATextItem("EstadoS", false);
		provider = new GHATextItem("Proveedor", false);
		
		itType = new GHATextItem("Tipo it", false);
		machineName = new GHATextItem("Nombre de la máquina", false);
		ipAddress = new GHATextItem("Dirección IP", false);
		macAddress = new GHATextItem("Dirección MAC", false);
		realWarrantySince = new GHATextItem("Garantía desde", false);
		realWarrantyTime = new GHATextItem("Período de garantía", false);
		realWarrantyPoT = new GHATextItem("realWarrantyPoT", false);
		realWarrantyBegin = new GHATextItem("Garantía comienza", false);
		intWarrantySince = new GHATextItem("Garantía desde", false);
		intWarrantyTime = new GHATextItem("Período de garantía", false);
		intWarrantyPoT = new GHATextItem("intWarrantyPoT", false);
		intWarrantyBegin = new GHATextItem("Garantía comienza", false);
		
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
		form.setNumCols(12);
		form.setItems(
			
				acceptationDate, actualCost, actualCostCurrency, adquisitionCost, adquisitionCostCurrency,
				adquisitionCostCurrencyLocal, adquisitionCostLocal, responsibleRole, code, contabilizationDate, dateLastDepreciation,
				depreciationMethod, depreciationTime, depreciationTimePoT, desincorporatedDate, desincorporateReason, eiaType, fixedAssetIdentifier,
				installationDate, installationProvider, lifeTime, lifeTimePoT, buildingLocation, attendedLocation, maintenanceLocation, maintenanceProvider,
				obu, purchaseDate, purchaseInvoiceDate, purchaseInvoiceNumber, purchaseOrderDate, purchaseOrderNumber, receptionDate, serialNumber, state, 
				provider, itType, machineName, ipAddress, macAddress, realWarrantySince, realWarrantyTime, realWarrantyPoT, realWarrantyBegin, intWarrantySince, 
				intWarrantyTime, intWarrantyPoT, intWarrantyBegin		
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