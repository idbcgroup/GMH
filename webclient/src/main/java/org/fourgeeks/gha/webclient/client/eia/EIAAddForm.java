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
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAAddForm extends GHASlideInWindow {

	private List<EIASelectionListener> listeners;
	private GHATextItem codeItem, serialItem, activeIdItem, facilityItem,
			nameFac, garantiaEstadoText;
	private GHASelectItem garantiaEstadoSelect;

	{
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
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);

		final GHASectionForm sectionForm = new GHASectionForm();
		sectionForm.addSection("Equipos", getEquiposForm());
		sectionForm.addSection("Areas", getAreasForm());
		sectionForm.addSection("Adquisicion", getAdquisicionForm());
		sectionForm.addSection("Mantenimiento", getMantenimientoForm());
		sectionForm.addSection("Garantias", getGarantiaInmForm());
		sectionForm.addSection("Costo", getCostoAdqTab());
		sectionForm.addSection("Areas", getCostoActual());
		sectionForm.addSection("EquiposIT", getEquiposForm());		
		
		VLayout sideButtons = GHAUiHelper.createBar(new GHAButton(
				"../resources/icons/new.png"), new GHAButton(
				"../resources/icons/edit.png"), new GHAButton(
				"../resources/icons/delete.png", new ClickHandler() {

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

	private DynamicForm getEquiposIT(){
		// //////EQUIPOS IT Form
		GHATitleTextItem itTitle = new GHATitleTextItem(
				"EQUIPOS IT (Sistemas):");
		GHASelectItem typeIT = new GHASelectItem("Tipo Equipo IT");
		typeIT.setWidth(150);
		GHASelectItem nombreMaquina = new GHASelectItem("Nombre Máquina");
		GHATextItem dirIP = new GHATextItem("Direccion IP");
		GHATextItem macAddress = new GHATextItem("MAC Address");

		DynamicForm equiposITForm = new DynamicForm();
		equiposITForm.setWidth("*");
		// equiposITForm.setIsGroup(true);
		// equiposITForm.setGroupTitle("EQUIPOS IT (Sistemas)");
		equiposITForm.setTitleOrientation(TitleOrientation.TOP);
		equiposITForm.setNumCols(6);

		equiposITForm.setItems(itTitle, typeIT, nombreMaquina, dirIP,
				macAddress);
		
		return equiposITForm;
	}
	
	private DynamicForm getCostoActual(){
		// //////Costo Actual form
		GHATitleTextItem actualCostTitle = new GHATitleTextItem("Costo Actual:");
		GHASelectItem metodoDepreciacion = new GHASelectItem(
				"Metodo Depreciación");
		metodoDepreciacion.setWidth(130);
		GHATextItem timeDep = new GHATextItem("Tiempo de");
		GHASelectItem timeDepSel = new GHASelectItem("Depreciacion");
		GHATextItem timeVida = new GHATextItem("Tiempo de");
		GHASelectItem timeVidaSel = new GHASelectItem("Depreciacion");
		GHATextItem costoAct = new GHATextItem("Costo Actual en libros");
		costoAct.setWidth(150);
		GHASelectItem monedaCosto = new GHASelectItem("Moneda");
		GHASpacerItem spacer = new GHASpacerItem(150);
		GHADateItem fechaDesinc = new GHADateItem("Fecha de Desincorporación");
		fechaDesinc.setWidth(150);
		GHATextItem motivoDesinc = new GHATextItem("Motivo de Desincorporacion");
		motivoDesinc.setWidth(160);

		DynamicForm costoActForm = new DynamicForm();
		costoActForm.setWidth("*");
		// costoActForm.setIsGroup(true);
		// costoActForm.setGroupTitle("Costo Actual");
		costoActForm.setTitleOrientation(TitleOrientation.TOP);
		costoActForm.setNumCols(8);

		costoActForm.setItems(actualCostTitle, metodoDepreciacion, timeDep,
				timeDepSel, timeVida, timeVidaSel, costoAct, monedaCosto,
				spacer, fechaDesinc, motivoDesinc);
		
		return costoActForm;
	}
	
	private DynamicForm getCostoAdqTab() {
		// //////Costo Adquisición form
		GHATitleTextItem adqCostTitle = new GHATitleTextItem(
				"Costo Adquisición:");
		GHATextItem costoAdq = new GHATextItem(
				"Costo de Adquisición del equipo");
		costoAdq.setWidth(180);
		GHASelectItem currencyAdq = new GHASelectItem("Moneda");
		GHATextItem fechaContab = new GHATextItem("Fecha de Contabilización");
		fechaContab.setWidth(180);
		GHATextItem costoAdqLoc = new GHATextItem("Costo de Adquisicion");
		costoAdqLoc.setWidth(150);
		GHASelectItem monedaLocal = new GHASelectItem("Moneda Local");

		DynamicForm costoAdqForm = new DynamicForm();
		costoAdqForm.setWidth("*");
		// costoAdqForm.setIsGroup(true);
		// costoAdqForm.setGroupTitle("Costo Adquisición");
		costoAdqForm.setTitleOrientation(TitleOrientation.TOP);
		costoAdqForm.setNumCols(6);

		costoAdqForm.setItems(adqCostTitle, costoAdq, currencyAdq, fechaContab,
				costoAdqLoc, monedaLocal);

			return costoAdqForm;
	}

	private DynamicForm getGarantiaInmForm(){
		GHATitleTextItem garantiaInmTitle = new GHATitleTextItem(
				"Garantía Inmediata:");
		GHASelectItem garantiaInmDesde = new GHASelectItem(
				"Garantia inmediata desde");
		garantiaInmDesde.setWidth(150);
		GHATextItem garantiaInm = new GHATextItem("Garantia");
		GHATextItem tiempoGarantiaInm = new GHATextItem("Tiempo");
		GHADateItem fechaInic = new GHADateItem("Fecha Inicio Grarantia");
		fechaInic.setWidth(150);
		GHATextItem intermediateGarantia = new GHATextItem(
				"En Garantía Intermedia");
		intermediateGarantia.setWidth(150);
		GHATextItem mesesGarantia = new GHATextItem("Meses");
		GHADateItem fechaAceptacion = new GHADateItem("Fecha Aceptación");

		DynamicForm garantiaInmForm = new DynamicForm();
		garantiaInmForm.setWidth("*");
		// garantiaInmForm.setIsGroup(true);
		// garantiaInmForm.setGroupTitle("Garantía intermedia");
		garantiaInmForm.setTitleOrientation(TitleOrientation.TOP);
		garantiaInmForm.setNumCols(8);

		garantiaInmForm.setItems(garantiaInmTitle, garantiaInmDesde,
				garantiaInm, tiempoGarantiaInm, fechaInic,
				intermediateGarantia, mesesGarantia, fechaAceptacion);
		
		return garantiaInmForm;
	}
	
	/**
	 * @return
	 */
	private DynamicForm getMantenimientoForm() {
		GHATitleTextItem mantenimientoTitle = new GHATitleTextItem(
				"Mantenimiento:");
		GHASelectItem garantiaDesde = new GHASelectItem("Garantia desde la");
		GHATextItem garantía = new GHATextItem("Garantia");
		GHATextItem tiempoGarantia = new GHATextItem("Tiempo");
		GHACheckboxItem isMant = new GHACheckboxItem("Equipo en Mantenimiento");
		GHATextItem codeMant = new GHATextItem("Cod. Ubicación Mantenimiento");
		codeMant.setWidth(170);
		GHATextItem nameMant = new GHATextItem("Nombre Ubicación Mantenimiento");
		nameMant.setWidth(180);
		GHATextItem providerMant = new GHATextItem("Proveedor de Mantenimiento");
		providerMant.setWidth(170);

		DynamicForm mantenimientoForm = new DynamicForm();
		mantenimientoForm.setWidth("*");
		// mantenimientoForm.setIsGroup(true);
		// mantenimientoForm.setGroupTitle("Mantenimiento");
		mantenimientoForm.setTitleOrientation(TitleOrientation.TOP);
		mantenimientoForm.setNumCols(8);

		mantenimientoForm.setItems(mantenimientoTitle, garantiaDesde, garantía,
				tiempoGarantia, isMant, codeMant, nameMant, providerMant);
		return mantenimientoForm;
	}

	
	/**
	 * @return
	 */
	private DynamicForm getAdquisicionForm() {
		// //////Adquisicion Form
		GHATitleTextItem adqisicionTitle = new GHATitleTextItem("Adquisición:");
		GHADateItem buyDate = new GHADateItem("Fecha de Compra");
		GHATextItem provider = new GHATextItem("Proveedor");
		GHATextItem noOrden = new GHATextItem("No. Orden Compra");
		noOrden.setWidth(130);
		GHATextItem noFactura = new GHATextItem("No. Factura");
		GHADateItem recepcionDate = new GHADateItem("Recepción");
		GHADateItem instalacionDate = new GHADateItem("Instalación");

		DynamicForm adquisicionForm = new DynamicForm();
		adquisicionForm.setWidth("*");
		// adquisicionForm.setIsGroup(true);
		// adquisicionForm.setGroupTitle("Adquisición");
		adquisicionForm.setTitleOrientation(TitleOrientation.TOP);
		adquisicionForm.setNumCols(7);

		adquisicionForm.setItems(adqisicionTitle, buyDate, provider, noOrden,
				noFactura, recepcionDate, instalacionDate);
		return adquisicionForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getAreasForm() {
		GHACheckboxItem variasAreas = new GHACheckboxItem(
				"Con el Equipo se atienden varias Áreas de trabajo");
		GHATextItem codeArea = new GHATextItem("Código");
		GHATextItem nameArea = new GHATextItem("Nombre");

		DynamicForm areaForm = new DynamicForm();
		areaForm.setWidth("*");
		areaForm.setTitleOrientation(TitleOrientation.TOP);
		areaForm.setNumCols(1);

		areaForm.setItems(variasAreas, codeArea, nameArea);
		return areaForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getEquiposForm() {
		GHATextItem depResponsable = new GHATextItem("Departamento Responsable");
		depResponsable.setColSpan(2);
		depResponsable.setWidth("250px");

		GHATextItem dirResponsable = new GHATextItem("Responsable Directo");
		dirResponsable.setColSpan(2);
		dirResponsable.setWidth("250px");

		DynamicForm equipoForm = new DynamicForm();
		equipoForm.setWidth("*");
		// equipoForm.setIsGroup(true);
		// equipoForm.setGroupTitle("Equipo (EIA)");
		equipoForm.setTitleOrientation(TitleOrientation.TOP);
		equipoForm.setNumCols(4);

		equipoForm.setItems(codeItem, serialItem, activeIdItem, facilityItem,
				nameFac, garantiaEstadoText, garantiaEstadoSelect,
				depResponsable, dirResponsable);
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
		// TODO Auto-generated method stub

	}
}
