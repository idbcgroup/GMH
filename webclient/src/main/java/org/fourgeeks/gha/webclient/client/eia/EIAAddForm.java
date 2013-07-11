package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHACheckboxItem;
import org.fourgeeks.gha.webclient.client.UI.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.GHASectionForm;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.UI.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHATitleTextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAAddForm extends GHASlideInWindow implements ResizeHandler {

	private List<EIASelectionListener> listeners;
	private GHATextItem codeItem, serialItem, activeIdItem,	garantiaEstadoText,
						noOrden,noFactura,codeAreaActual,codeAreaAtendida,costoAdq,
						costoAdqLoc,timeDep,timeVida,costoAct,garantiaReal,
						tiempoGarantiaReal,garantiaInt,tiempoGarantiaInt,
						intermediateGarantia,mesesGarantia,codeMant,nameMant,
						providerMant,numMant,dirIPTextItem,macAddressTextItem;
	private GHASelectItem garantiaEstadoSelect, depResponsableSelectItem,dirResponsable,
						  provider,nameAreaActual,nameAreaAtendida,currencyAdq,
						  monedaLocal,metodoDepreciacion,timeDepSel,timeVidaSel,
						  monedaCosto,garantiaDesde,garantiaIntDesde,typeITSelectItem,
						  nombreMaquinaSelectItem;							
	private GHATitleTextItem equiposTitle,garantiasTitle,adqisicionTitle,
						     areaActualTitle,areaAtendidaTitle,adqCostTitle,
						     actualCostTitle,tiempoDepTitle,tiempoVidaTitle,
						     garantiaRealTitle,garantiaIntTitle,mantenimientoTitle,
						     itTitleTextItem;
	private GHADateItem buyDate,recepcionDate,instalacionDate,fechaContab,fechaUltDeprec,
						fechaInic,fechaInicInt,fechaInicMant,fechaFinMant;
	private GHACheckboxItem mismaArea,isMant,retired;
	private GHASectionForm sectionForm;
	{
		//Global
		sectionForm = new GHASectionForm();
		listeners = new ArrayList<EIASelectionListener>();
		//Information Form Items
		equiposTitle = new GHATitleTextItem("Información:");
		garantiasTitle = new GHATitleTextItem("Estado:");
		codeItem = new GHATextItem("Código");
		serialItem = new GHATextItem("Serial");
		activeIdItem = new GHATextItem("Id. Activo Fijo");
		depResponsableSelectItem = new GHASelectItem("Departamento Responsable");
		depResponsableSelectItem.setColSpan(2);
		depResponsableSelectItem.setWidth(200);
		dirResponsable = new GHASelectItem("Rol Responsable");
		dirResponsable.setColSpan(2);
		dirResponsable.setWidth(200);
		garantiaEstadoText = new GHATextItem("En Garantía");
		garantiaEstadoSelect = new GHASelectItem("Estado Equipo");
		//Adquisicion Form Items
		adqisicionTitle = new GHATitleTextItem("Adquisición:");
		buyDate = new GHADateItem("Fecha de Compra");
		provider = new GHASelectItem("Proveedor");
		noOrden = new GHATextItem("No. Orden Compra");
		noOrden.setWidth(130);
		noFactura = new GHATextItem("No. Factura");
		recepcionDate = new GHADateItem("Recepción");
		instalacionDate = new GHADateItem("Instalación");
		//Ubicacion Form Items
		areaActualTitle = new GHATitleTextItem("Area Actual:");
		areaAtendidaTitle = new GHATitleTextItem("Area Atendida:");
		codeAreaActual = new GHATextItem("Código",false);
		codeAreaActual.setWidth(120);
		nameAreaActual = new GHASelectItem("Nombre");
		nameAreaActual.setWidth(150);
		codeAreaAtendida = new GHATextItem("Código",false);
		codeAreaAtendida.setWidth(120);
		nameAreaAtendida = new GHASelectItem("Nombre");
		nameAreaAtendida.setWidth(150);
		mismaArea = new GHACheckboxItem("Atiende a la misma area donde esta Ubicado");
		mismaArea.setColSpan(2);
		//Costos Form Items
		adqCostTitle = new GHATitleTextItem("Costo Adquisición:");
		costoAdq = new GHATextItem("Costo de Adquisición del equipo");
		costoAdq.setWidth(180);
		currencyAdq = new GHASelectItem("Moneda");
		currencyAdq.setWidth(70);
		fechaContab = new GHADateItem("Fecha de Contabilización");
		fechaContab.setWidth(180);
		costoAdqLoc = new GHATextItem("Costo de Adquisicion");
		costoAdqLoc.setWidth(150);
		monedaLocal = new GHASelectItem("Moneda Local");
		monedaLocal.setWidth(70);
		actualCostTitle = new GHATitleTextItem("Costo Actual:");
		metodoDepreciacion = new GHASelectItem("Metodo Depreciación");
		metodoDepreciacion.setWidth(130);
		tiempoDepTitle = new GHATitleTextItem("Tiempo de Depreciacion:");
		timeDep = new GHATextItem("Cantidad");
		timeDepSel = new GHASelectItem("Unidad");
		tiempoVidaTitle = new GHATitleTextItem("Tiempo de Vida:");
		timeVida = new GHATextItem("Cantidad");
		timeVidaSel = new GHASelectItem("Unidad");
		costoAct = new GHATextItem("Costo Actual en libros");
		costoAct.setWidth(150);
		monedaCosto = new GHASelectItem("Moneda");
		fechaUltDeprec = new GHADateItem("Fecha Ult. Depreciación");
		fechaUltDeprec.setWidth(150);
		//Garantias Form Items
		garantiaRealTitle = new GHATitleTextItem("Garantía Real:");
		garantiaIntTitle = new GHATitleTextItem("Garantía Intermedia:");
		mantenimientoTitle = new GHATitleTextItem("Mantenimiento:");
		garantiaDesde = new GHASelectItem("Garantía Real desde la");
		garantiaDesde.setWidth(130);
		garantiaReal = new GHATextItem("Garantía");
		tiempoGarantiaReal = new GHATextItem("Tiempo");
		fechaInic = new GHADateItem("Fecha Inicio");
		garantiaIntDesde = new GHASelectItem("Garantía Real desde la");
		garantiaIntDesde.setWidth(130);
		garantiaInt = new GHATextItem("Garantía");
		tiempoGarantiaInt = new GHATextItem("Tiempo");
		fechaInicInt = new GHADateItem("Fecha Inicio");
		intermediateGarantia = new GHATextItem("En Garantía Intermedia");
		intermediateGarantia.setWidth(130);
		mesesGarantia = new GHATextItem("Meses");
		isMant = new GHACheckboxItem("Equipo en Mantenimiento");
		codeMant = new GHATextItem("Cod. Ubicación Mant.");
		codeMant.setWidth(130);
		nameMant = new GHATextItem("Nombre Ubicación Mant.");
		nameMant.setWidth(130);
		providerMant = new GHATextItem("Proveedor de Mant.");
		providerMant.setWidth(130);
		retired = new GHACheckboxItem("Prov. Retiró Equipo en Mant.");
		numMant = new GHATextItem("Mant. Efectuados");
		numMant.setWidth(130);
		fechaInicMant = new GHADateItem("Fecha Inic. Mant.");
		fechaInicMant.setWidth(130);
		fechaFinMant = new GHADateItem("Fecha Fin. Mant.");
		fechaFinMant.setWidth(130);
		//Equipos IT Form Items
		itTitleTextItem = new GHATitleTextItem("EQUIPOS IT (Sistemas):");
		typeITSelectItem = new GHASelectItem("Tipo Equipo IT");
		typeITSelectItem.setWidth(150);
		nombreMaquinaSelectItem = new GHASelectItem("Nombre Máquina");
		dirIPTextItem = new GHATextItem("Direccion IP");
		macAddressTextItem = new GHATextItem("MAC Address");
	}

	public EIAAddForm() {
		GHAUiHelper.addResizeHandler(this);
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);

		sectionForm.addSection("Información Básica", getInfoBasicaForm(), true);
		sectionForm.addSectionSeparator();
		sectionForm.addSection("Adquisicion", getAdquisicionForm(), false);
		sectionForm.addSection("Ubicación", getUbicacionForm(), false);
		sectionForm.addSection("Costos", getCostosTab(), false);
		sectionForm.addSection("Garantias", getGarantiasMantForm(), false);
		sectionForm.addSection("EquiposIT", getEquiposIT(), false);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAButton(
				"../resources/icons/save.png"), new GHAButton(
				"../resources/icons/set.png"), new GHAButton(
				"../resources/icons/cancel.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						EIAAddForm.this.animateHide(AnimationEffect.FLY);
						sectionForm.deactivate();

					}
				}));

		HLayout mainLayout = new HLayout();
		mainLayout.addMember(sectionForm);
		mainLayout.addMembers(new LayoutSpacer(), sideButtons);
		Label title = new Label("<h3>Nuevo equipo</h3>");
		title.setWidth(400);
		title.setHeight("35px");
		addMember(title);
		addMember(mainLayout);
		
		//Fillers 
		fillBuildinglocationsSelects();
		fillInformationSelects();
	}

////Form Creating Functions
	
	private DynamicForm getEquiposIT() {
		DynamicForm equiposITForm = new DynamicForm();
		equiposITForm.setTitleOrientation(TitleOrientation.TOP);
		equiposITForm.setNumCols(2);

		equiposITForm.setItems(itTitleTextItem, new GHASpacerItem(), 
							   typeITSelectItem,nombreMaquinaSelectItem, 
							   dirIPTextItem, macAddressTextItem);

		return equiposITForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getGarantiasMantForm() {
		DynamicForm garantiasMamtenimientoForm = new DynamicForm();
		garantiasMamtenimientoForm.setTitleOrientation(TitleOrientation.TOP);
		garantiasMamtenimientoForm.setNumCols(4);

		garantiasMamtenimientoForm.setItems(
				garantiaRealTitle,new GHASpacerItem(3), 
				garantiaDesde, garantiaReal,tiempoGarantiaReal, fechaInic, 
				garantiaIntTitle,new GHASpacerItem(3), 
				garantiaIntDesde, garantiaInt,tiempoGarantiaInt, fechaInicInt, 
				intermediateGarantia,mesesGarantia, new GHASpacerItem(2), 
				new GHASpacerItem(4),
				mantenimientoTitle, new GHASpacerItem(3), 
				isMant, codeMant,nameMant, providerMant,
				retired, numMant, fechaInicMant,fechaFinMant);

		return garantiasMamtenimientoForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getCostosTab() {
		DynamicForm costosForm = new DynamicForm();
		costosForm.setTitleOrientation(TitleOrientation.TOP);
		costosForm.setNumCols(3);

		costosForm.setItems(adqCostTitle, new GHASpacerItem(2), 
							costoAdq, currencyAdq, fechaContab, 
							costoAdqLoc,monedaLocal,new GHASpacerItem(), 
							new GHASpacerItem(3), 
							actualCostTitle,new GHASpacerItem(2), 
							metodoDepreciacion, fechaUltDeprec,new GHASpacerItem(), 
							tiempoDepTitle, timeDep, timeDepSel,
							tiempoVidaTitle, timeVida, timeVidaSel, 
							costoAct, monedaCosto);

		return costosForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getUbicacionForm() {
		DynamicForm areaForm = new DynamicForm();
		areaForm.setTitleOrientation(TitleOrientation.TOP);
		areaForm.setNumCols(2);

		areaForm.setItems(areaActualTitle, new GHASpacerItem(), 
						  codeAreaActual,nameAreaActual, 
						  mismaArea, 
						  areaAtendidaTitle, new GHASpacerItem(), 
						  codeAreaAtendida, nameAreaAtendida);
		return areaForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getAdquisicionForm() {
		// //////Adquisicion Form
		DynamicForm adquisicionForm = new DynamicForm();
		adquisicionForm.setTitleOrientation(TitleOrientation.TOP);
		adquisicionForm.setNumCols(3);

		adquisicionForm.setItems(adqisicionTitle, new GHASpacerItem(2),
								 buyDate, recepcionDate, instalacionDate, 
								 provider, noOrden, noFactura);
		return adquisicionForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getInfoBasicaForm() {
		DynamicForm equipoForm = new DynamicForm();
		equipoForm.setTitleOrientation(TitleOrientation.TOP);
		equipoForm.setNumCols(4);

		equipoForm.setItems(equiposTitle, new GHASpacerItem(3), 
							codeItem,serialItem,activeIdItem, new GHASpacerItem(), 
							depResponsableSelectItem,dirResponsable, 
							garantiasTitle, new GHASpacerItem(3),
							garantiaEstadoText, garantiaEstadoSelect);
		return equipoForm;
	}
	
////Fillers
	
	private void fillBuildinglocationsSelects() {
		GHACache.INSTANCE
				.getBuildingLocations(new GHAAsyncCallback<List<BuildingLocation>>() {
					@Override
					public void onSuccess(List<BuildingLocation> result) {
						LinkedHashMap<String, String> valueMapActual = new LinkedHashMap<String, String>();
						LinkedHashMap<String, String> valueMapAtendida = new LinkedHashMap<String, String>();
						
						for (BuildingLocation entity : result){
							valueMapActual.put(entity.getId() + "",
									entity.getLocationName());
							valueMapAtendida.put(entity.getId() + "",
									entity.getLocationName());
						}
						
						nameAreaActual.setValueMap(valueMapActual);
						nameAreaAtendida.setValueMap(valueMapAtendida);

					}
				});
	}
	
	private void fillInformationSelects(){
		GHACache.INSTANCE.getObus(new GHAAsyncCallback<List<Obu>>() {
			@Override
			public void onSuccess(List<Obu> result) {				
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for(Obu entity: result)
					valueMap.put(entity.getId()+"", entity.getName()+"");
				depResponsableSelectItem.setValueMap(valueMap);
			}
		});
	}
	
////Implementations
	
	protected void select(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}

	public void addEiaSelectionListener(EIASelectionListener listener) {
		listeners.add(listener);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

}
