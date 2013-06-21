package org.fourgeeks.gha.webclient.client.eia.equipos;

import org.fourgeeks.gha.webclient.client.UI.GHACheckboxItem;
import org.fourgeeks.gha.webclient.client.UI.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHATitleTextItem;

import com.google.gwt.user.client.Event;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAEquiposCreateForm extends HLayout {

	public EIAEquiposCreateForm() {
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
//		setBorder("2px solid #484848");
////////Equipos Form
		
		GHATextItem codigoEIA = new GHATextItem("Código de equipo");
		GHATextItem serialEIA = new GHATextItem("No. Serial");
		GHATextItem idActivoF = new GHATextItem("Id. Activo Fijo");
		GHATextItem codigoFac = new GHATextItem("Ubicación: Cod.");
		GHATextItem nameFac = new GHATextItem("Nombre");
		GHATextItem garantiaEstadoText = new GHATextItem("En Garantía");
		GHASelectItem garantiaEstadoSelect = new GHASelectItem("Estado Equipo");

		GHATextItem depResponsable = new GHATextItem("Departamento Responsable");
		depResponsable.setColSpan(2);
		depResponsable.setWidth("250px");
		
		GHATextItem dirResponsable = new GHATextItem("Responsable Directo");
		dirResponsable.setColSpan(2);
		dirResponsable.setWidth("250px");
		
		DynamicForm equipoForm = new DynamicForm();
		equipoForm.setWidth("*");
//		equipoForm.setIsGroup(true);
//		equipoForm.setGroupTitle("Equipo (EIA)");
		equipoForm.setTitleOrientation(TitleOrientation.TOP);
		equipoForm.setNumCols(7);
		
		equipoForm.setItems(codigoEIA, serialEIA, idActivoF, codigoFac, nameFac,garantiaEstadoText,garantiaEstadoSelect,
					  		depResponsable,dirResponsable);

////////Areas de trabajo Atendidas form
		GHATitleTextItem workingAreaTitle = new GHATitleTextItem("Areas de Trabajo Atendidas:");
		GHACheckboxItem variasAreas = new GHACheckboxItem("Con el Equipo se atienden varias Áreas de trabajo");
		GHATextItem codeArea = new GHATextItem("Código");
		GHATextItem nameArea = new GHATextItem("Nombre");
		
		DynamicForm areaForm = new DynamicForm();
		areaForm.setWidth("*");
//		areaForm.setIsGroup(true);
//		areaForm.setGroupTitle("Areas de Trabajo Atendidas");
		areaForm.setTitleOrientation(TitleOrientation.TOP);
		areaForm.setNumCols(4);
		
		areaForm.setItems(workingAreaTitle, variasAreas, codeArea, nameArea);
		
////////Adquisicion Form		
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
//		adquisicionForm.setIsGroup(true);
//		adquisicionForm.setGroupTitle("Adquisición");
		adquisicionForm.setTitleOrientation(TitleOrientation.TOP);
		adquisicionForm.setNumCols(7);
		
		adquisicionForm.setItems(adqisicionTitle, buyDate, provider,noOrden, noFactura, recepcionDate, instalacionDate);

////////Mantenimiento Form
		GHATitleTextItem mantenimientoTitle = new GHATitleTextItem("Mantenimiento:");
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
//		mantenimientoForm.setIsGroup(true);
//		mantenimientoForm.setGroupTitle("Mantenimiento");
		mantenimientoForm.setTitleOrientation(TitleOrientation.TOP);
		mantenimientoForm.setNumCols(8);
		
		mantenimientoForm.setItems(mantenimientoTitle, garantiaDesde, garantía,tiempoGarantia, isMant, codeMant, nameMant, providerMant);
		
////////Garantía inmediata form
		GHATitleTextItem garantiaInmTitle = new GHATitleTextItem("Garantía Inmediata:");
		GHASelectItem garantiaInmDesde = new GHASelectItem("Garantia inmediata desde");
		garantiaInmDesde.setWidth(150);
		GHATextItem garantiaInm = new GHATextItem("Garantia");
		GHATextItem tiempoGarantiaInm = new GHATextItem("Tiempo");
		GHADateItem fechaInic = new GHADateItem("Fecha Inicio Grarantia");
		fechaInic.setWidth(150);
		GHATextItem intermediateGarantia = new GHATextItem("En Garantía Intermedia");
		intermediateGarantia.setWidth(150);
		GHATextItem mesesGarantia = new GHATextItem("Meses");
		GHADateItem fechaAceptacion = new GHADateItem("Fecha Aceptación");
		
		DynamicForm garantiaInmForm = new DynamicForm();
		garantiaInmForm.setWidth("*");
//		garantiaInmForm.setIsGroup(true);
//		garantiaInmForm.setGroupTitle("Garantía intermedia");
		garantiaInmForm.setTitleOrientation(TitleOrientation.TOP);
		garantiaInmForm.setNumCols(8);
		
		garantiaInmForm.setItems(garantiaInmTitle, garantiaInmDesde, garantiaInm, tiempoGarantiaInm, fechaInic, intermediateGarantia, mesesGarantia, fechaAceptacion);
		
////////Costo Adquisición form
		GHATitleTextItem adqCostTitle = new GHATitleTextItem("Costo Adquisición:");
		GHATextItem costoAdq = new GHATextItem("Costo de Adquisición del equipo");
		costoAdq.setWidth(180);
		GHASelectItem currencyAdq = new GHASelectItem("Moneda");
		GHATextItem fechaContab = new GHATextItem("Fecha de Contabilización");
		fechaContab.setWidth(180);
		GHATextItem costoAdqLoc = new GHATextItem("Costo de Adquisicion");
		costoAdqLoc.setWidth(150);
		GHASelectItem monedaLocal = new GHASelectItem("Moneda Local");
		
		DynamicForm costoAdqForm = new DynamicForm();
		costoAdqForm.setWidth("*");
//		costoAdqForm.setIsGroup(true);
//		costoAdqForm.setGroupTitle("Costo Adquisición");
		costoAdqForm.setTitleOrientation(TitleOrientation.TOP);
		costoAdqForm.setNumCols(6);
		
		costoAdqForm.setItems(adqCostTitle, costoAdq, currencyAdq, fechaContab, costoAdqLoc, monedaLocal);
		
////////Costo Actual form
		GHATitleTextItem actualCostTitle = new GHATitleTextItem("Costo Actual:");
		GHASelectItem metodoDepreciacion = new GHASelectItem("Metodo Depreciación");
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
//		costoActForm.setIsGroup(true);
//		costoActForm.setGroupTitle("Costo Actual");
		costoActForm.setTitleOrientation(TitleOrientation.TOP);
		costoActForm.setNumCols(8);
		
		costoActForm.setItems(actualCostTitle, metodoDepreciacion, timeDep, timeDepSel, timeVida, timeVidaSel, costoAct, monedaCosto, spacer, fechaDesinc, motivoDesinc);
		
////////EQUIPOS IT Form
		GHATitleTextItem itTitle = new GHATitleTextItem("EQUIPOS IT (Sistemas):");
		GHASelectItem typeIT = new GHASelectItem("Tipo Equipo IT");
		typeIT.setWidth(150);
		GHASelectItem nombreMaquina = new GHASelectItem("Nombre Máquina");
		GHATextItem dirIP = new GHATextItem("Direccion IP");
		GHATextItem macAddress = new GHATextItem("MAC Address");
		
		DynamicForm equiposITForm = new DynamicForm();
		equiposITForm.setWidth("*");
//		equiposITForm.setIsGroup(true);
//		equiposITForm.setGroupTitle("EQUIPOS IT (Sistemas)");
		equiposITForm.setTitleOrientation(TitleOrientation.TOP);
		equiposITForm.setNumCols(6);
		
		equiposITForm.setItems(itTitle, typeIT, nombreMaquina, dirIP, macAddress);

		
//////////////////////////////
		Label title = new Label("<h3>Detalle de características de Equipos</h3>");
		title.setWidth(400);
		title.setHeight("35px");
		
		VLayout main = new VLayout();
		main.setMembersMargin(5);
		main.addMembers(title, equipoForm, areaForm, adquisicionForm, mantenimientoForm, garantiaInmForm, costoAdqForm, costoActForm, equiposITForm);		
		
		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		// botones1.setBackgroundImage("../resources/img/botonBox.jpg");
		sideButtons.setBackgroundColor("#E0E0E0");
		sideButtons.setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);

		ImgButton addButton = new ImgButton();
		addButton.sinkEvents(Event.MOUSEEVENTS);
		addButton.setSrc("../resources/icons/new.png");
		addButton.setShowRollOver(false);

		addButton.setSize("20px", "20px");
		Img editButton = new Img("../resources/icons/edit.png");
		editButton.setSize("20px", "20px");

		ImgButton cancelButton = new ImgButton();
		cancelButton.setSrc("../resources/icons/delete.png");
		cancelButton.setSize("20px", "20px");
		cancelButton.setShowRollOver(false);
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				EIAEquiposCreateForm.this.animateHide(AnimationEffect.FLY);
			}
		});

		sideButtons.addMembers(addButton, editButton, cancelButton);
		
		addMembers(main, sideButtons);
	}
}
