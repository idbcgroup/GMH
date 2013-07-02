package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAButton;
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
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAAddForm extends GHASlideInWindow implements ResizeHandler{

	private List<EIASelectionListener> listeners;
	private GHATextItem codeItem, serialItem, activeIdItem, facilityItem,
			nameFac, garantiaEstadoText;
	private GHASelectItem garantiaEstadoSelect;
	private GHASectionForm sectionForm;	
	{
		sectionForm = new GHASectionForm();
		listeners = new ArrayList<EIASelectionListener>();
		codeItem = new GHATextItem("Código");
		serialItem = new GHATextItem("Serial");
		activeIdItem = new GHATextItem("Id. Activo Fijo");
		facilityItem = new GHATextItem("Ubicación");
		nameFac = new GHATextItem("Nombre");
		garantiaEstadoText = new GHATextItem("En Garantía");
		garantiaEstadoSelect = new GHASelectItem("Estado Equipo");
	}

	public EIAAddForm() {
		GHAUiHelper.addResizeHandler(this);
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);

		sectionForm.addSection("Información Básica", getInfoBasicaForm(),true);
		sectionForm.addSectionSeparator();
		sectionForm.addSection("Adquisicion", getAdquisicionForm(),false);
		sectionForm.addSection("Ubicación", getUbicacionForm(),false);
		sectionForm.addSection("Costos", getCostosTab(),false);
		sectionForm.addSection("Garantias", getGarantiasMantForm(),false);
		sectionForm.addSection("EquiposIT", getEquiposIT(),false);		
		
		VLayout sideButtons = GHAUiHelper.createBar(new GHAButton(
				"../resources/icons/new.png"), new GHAButton(
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

	}

	public GHASectionForm getSectionForm() {
		return sectionForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getEquiposIT(){
		// //////EQUIPOS IT Form
		GHATitleTextItem itTitle = new GHATitleTextItem("EQUIPOS IT (Sistemas):");
		GHASelectItem typeIT = new GHASelectItem("Tipo Equipo IT");
		typeIT.setWidth(150);
		GHASelectItem nombreMaquina = new GHASelectItem("Nombre Máquina");
		GHATextItem dirIP = new GHATextItem("Direccion IP");
		GHATextItem macAddress = new GHATextItem("MAC Address");

		DynamicForm equiposITForm = new DynamicForm();	
		equiposITForm.setTitleOrientation(TitleOrientation.TOP);
		equiposITForm.setNumCols(2);

		equiposITForm.setItems(itTitle, new GHASpacerItem(), 
							   typeIT, nombreMaquina,
							   dirIP, macAddress);
		
		return equiposITForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getGarantiasMantForm(){
		GHATitleTextItem garantiasTitle = new GHATitleTextItem("Garantías:");
		GHATitleTextItem garantiaRealTitle = new GHATitleTextItem("Garantía Real:");
		GHATitleTextItem garantiaIntTitle = new GHATitleTextItem("Garantía Intermedia:");
		GHATitleTextItem mantenimientoTitle = new GHATitleTextItem("Mantenimiento:");
		
		/////Garantía Real
		GHASelectItem garantiaDesde = new GHASelectItem("Garantía Real desde la");
		garantiaDesde.setWidth(130);
		GHATextItem garantiaReal = new GHATextItem("Garantía");
		GHATextItem tiempoGarantiaReal = new GHATextItem("Tiempo");
		GHADateItem fechaInic = new GHADateItem("Fecha Inicio");
		
		//// Garantía Intermedia
		GHASelectItem garantiaIntDesde = new GHASelectItem("Garantía Real desde la");
		garantiaIntDesde.setWidth(130);
		GHATextItem garantiaInt = new GHATextItem("Garantía");
		GHATextItem tiempoGarantiaInt = new GHATextItem("Tiempo");
		GHADateItem fechaInicInt = new GHADateItem("Fecha Inicio");
				
		GHATextItem intermediateGarantia = new GHATextItem("En Garantía Intermedia");
		intermediateGarantia.setWidth(130);
		GHATextItem mesesGarantia = new GHATextItem("Meses");

		//// Mantenimiento
		GHACheckboxItem isMant = new GHACheckboxItem("Equipo en Mantenimiento");
		GHATextItem codeMant = new GHATextItem("Cod. Ubicación Mant.");
		codeMant.setWidth(130);
		GHATextItem nameMant = new GHATextItem("Nombre Ubicación Mant.");
		nameMant.setWidth(130);
		GHATextItem providerMant = new GHATextItem("Proveedor de Mant.");
		providerMant.setWidth(130);
		GHACheckboxItem retired = new GHACheckboxItem("Prov. Retiró Equipo en Mant.");
		GHATextItem numMant = new GHATextItem("Mant. Efectuados");
		numMant.setWidth(130);
		GHADateItem fechaInicMant = new GHADateItem("Fecha Inic. Mant.");
		fechaInicMant.setWidth(130);
		GHADateItem fechaFinMant = new GHADateItem("Fecha Fin. Mant.");
		fechaFinMant.setWidth(130);		
		
		DynamicForm garantiasMamtenimientoForm = new DynamicForm();
		garantiasMamtenimientoForm.setTitleOrientation(TitleOrientation.TOP);
		garantiasMamtenimientoForm.setNumCols(4);

		garantiasMamtenimientoForm.setItems(
			garantiaRealTitle, new GHASpacerItem(3),
			garantiaDesde, garantiaReal, tiempoGarantiaReal, fechaInic,
			garantiaIntTitle, new GHASpacerItem(3),
			garantiaIntDesde, garantiaInt, tiempoGarantiaInt, fechaInicInt,
			intermediateGarantia, mesesGarantia, new GHASpacerItem(2), 
			new GHASpacerItem(4),
			mantenimientoTitle, new GHASpacerItem(3),
			isMant, codeMant, nameMant, providerMant, 
			retired,numMant, fechaInicMant, fechaFinMant				
			);
		
		
		return garantiasMamtenimientoForm;
	}
	
	/**
	 * @return
	 */
	private DynamicForm getCostosTab() {
		////////Costo Adquisición form
		GHATitleTextItem adqCostTitle = new GHATitleTextItem(
				"Costo Adquisición:");
		GHATextItem costoAdq = new GHATextItem(
				"Costo de Adquisición del equipo");
		costoAdq.setWidth(180);
		GHASelectItem currencyAdq = new GHASelectItem("Moneda");
		currencyAdq.setWidth(70);
		GHADateItem fechaContab = new GHADateItem("Fecha de Contabilización");
		fechaContab.setWidth(180);
		GHATextItem costoAdqLoc = new GHATextItem("Costo de Adquisicion");
		costoAdqLoc.setWidth(150);
		GHASelectItem monedaLocal = new GHASelectItem("Moneda Local");
		monedaLocal.setWidth(70);

		// //////Costo Actual form
		GHATitleTextItem actualCostTitle = new GHATitleTextItem("Costo Actual:");
		GHASelectItem metodoDepreciacion = new GHASelectItem("Metodo Depreciación");
		metodoDepreciacion.setWidth(130);
		
		GHATitleTextItem tiempoDepTitle = new GHATitleTextItem("Tiempo de Depreciacion:");
		GHATextItem timeDep = new GHATextItem("Cantidad");
		GHASelectItem timeDepSel = new GHASelectItem("Unidad");
		GHATitleTextItem tiempoVidaTitle = new GHATitleTextItem("Tiempo de Vida:");
		GHATextItem timeVida = new GHATextItem("Cantidad");
		GHASelectItem timeVidaSel = new GHASelectItem("Unidad");
		
		GHATextItem costoAct = new GHATextItem("Costo Actual en libros");
		costoAct.setWidth(150);
		GHASelectItem monedaCosto = new GHASelectItem("Moneda");
		
		GHADateItem fechaUltDeprec = new GHADateItem("Fecha Ult. Depreciación");
		fechaUltDeprec.setWidth(150);

		//Form
		
		DynamicForm costosForm = new DynamicForm();
		costosForm.setTitleOrientation(TitleOrientation.TOP);
		costosForm.setNumCols(3);
		
		costosForm.setItems(adqCostTitle, new GHASpacerItem(2),  
							costoAdq, currencyAdq, fechaContab,
							costoAdqLoc, monedaLocal, new GHASpacerItem(),
	            			new GHASpacerItem(3),
							actualCostTitle, new GHASpacerItem(2),
							metodoDepreciacion, fechaUltDeprec,  new GHASpacerItem(),
							tiempoDepTitle,timeDep, timeDepSel,
							tiempoVidaTitle, timeVida, timeVidaSel,
							costoAct, monedaCosto);

		return costosForm;
	}
	
	/**
	 * @return
	 */
	private DynamicForm getUbicacionForm() {

		GHATitleTextItem areaActualTitle = new GHATitleTextItem("Area Actual:");
		areaActualTitle.setAlign(Alignment.CENTER);
		GHATitleTextItem areaAtendidaTitle = new GHATitleTextItem("Area Atendida:");
		areaAtendidaTitle.setAlign(Alignment.CENTER);
		
		GHATextItem codeAreaActual = new GHATextItem("Código Area Actual");
		codeAreaActual.setWidth(150);
		GHATextItem nameAreaActual = new GHATextItem("Nombre Area Actual");
		nameAreaActual.setWidth(150);
		GHATextItem codeAreaAtendida = new GHATextItem("Código Area Atendida");
		codeAreaAtendida.setWidth(150);
		GHATextItem nameAreaAtendida = new GHATextItem("Nombre Area Atendida");
		nameAreaAtendida.setWidth(150);
		
		GHACheckboxItem mismaArea = new GHACheckboxItem(
				"Atiende a la misma area donde esta Ubicado");
		mismaArea.setColSpan(2);
		
		DynamicForm areaForm = new DynamicForm();
		areaForm.setTitleOrientation(TitleOrientation.TOP);
		areaForm.setNumCols(2);

		areaForm.setItems(areaActualTitle, new GHASpacerItem(),
						  codeAreaActual, nameAreaActual,
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
		GHATitleTextItem adqisicionTitle = new GHATitleTextItem("Adquisición:");
		GHADateItem buyDate = new GHADateItem("Fecha de Compra");
		GHASelectItem provider = new GHASelectItem("Proveedor");
		GHATextItem noOrden = new GHATextItem("No. Orden Compra");
		noOrden.setWidth(130);
		GHATextItem noFactura = new GHATextItem("No. Factura");
		GHADateItem recepcionDate = new GHADateItem("Recepción");
		GHADateItem instalacionDate = new GHADateItem("Instalación");

		DynamicForm adquisicionForm = new DynamicForm();
		adquisicionForm.setTitleOrientation(TitleOrientation.TOP);
		adquisicionForm.setNumCols(3);

		adquisicionForm.setItems(adqisicionTitle, new GHASpacerItem(2), 
								 buyDate, recepcionDate, instalacionDate,
								 provider, noOrden,	noFactura);
		return adquisicionForm;
	}
	
	/**
	 * @return
	 */
	private DynamicForm getInfoBasicaForm() {
		GHATitleTextItem equiposTitle = new GHATitleTextItem("Información:");
		GHATitleTextItem garantiasTitle = new GHATitleTextItem("Estado:");
		
		GHASelectItem depResponsable = new GHASelectItem("Departamento Responsable");
		depResponsable.setColSpan(2);
		depResponsable.setWidth(200);

		GHASelectItem dirResponsable = new GHASelectItem("Rol Responsable");
		dirResponsable.setColSpan(2);
		dirResponsable.setWidth(200);

		DynamicForm equipoForm = new DynamicForm();
		equipoForm.setTitleOrientation(TitleOrientation.TOP);
		equipoForm.setNumCols(4);

		equipoForm.setItems(equiposTitle, new GHASpacerItem(3), 
							codeItem, serialItem, activeIdItem, new GHASpacerItem(),
							depResponsable, dirResponsable,
							garantiasTitle, new GHASpacerItem(3), 
							garantiaEstadoText, garantiaEstadoSelect);
		return equipoForm;
	}

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
