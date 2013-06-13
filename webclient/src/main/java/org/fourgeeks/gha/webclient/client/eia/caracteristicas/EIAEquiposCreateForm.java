package org.fourgeeks.gha.webclient.client.eia.caracteristicas;

import org.fourgeeks.gha.webclient.client.UI.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;

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
		equipoForm.setIsGroup(true);
		equipoForm.setGroupTitle("Equipo (EIA)");
		equipoForm.setTitleOrientation(TitleOrientation.TOP);
		equipoForm.setNumCols(7);
		
		equipoForm.setItems(codigoEIA, serialEIA, idActivoF, codigoFac, nameFac,garantiaEstadoText,garantiaEstadoSelect,
					  		depResponsable,dirResponsable);

////////Areas de trabajo Atendidas form
		GHATextItem codeArea = new GHATextItem("Código");
		GHATextItem nameArea = new GHATextItem("Nombre");
		
		DynamicForm areaForm = new DynamicForm();
		areaForm.setWidth("*");
		areaForm.setIsGroup(true);
		areaForm.setGroupTitle("Areas de Trabajo Atendidas");
		areaForm.setTitleOrientation(TitleOrientation.TOP);
		areaForm.setNumCols(2);
		
		areaForm.setItems(codeArea, nameArea);
		
////////Adquisicion Form		
		GHADateItem buyDate = new GHADateItem("Fecha de Compra");
		GHATextItem provider = new GHATextItem("Proveedor");
		GHATextItem noOrden = new GHATextItem("No. Orden Compra");
		GHATextItem noFactura = new GHATextItem("No. Factura");
		GHADateItem recepcionDate = new GHADateItem("Recepción");
		GHADateItem instalacionDate = new GHADateItem("Instalación");
		
		DynamicForm adquisicionForm = new DynamicForm();
		adquisicionForm.setWidth("*");
		adquisicionForm.setIsGroup(true);
		adquisicionForm.setGroupTitle("Areas de Trabajo Atendidas");
		adquisicionForm.setTitleOrientation(TitleOrientation.TOP);
		adquisicionForm.setNumCols(6);
		
		adquisicionForm.setItems(buyDate, provider,noOrden, noFactura, recepcionDate, instalacionDate);
		
//////////////////////////////
		Label title = new Label("<h3>Detalle de características de Equipos</h3>");
		title.setWidth(400);
		title.setHeight("35px");
		
		VLayout main = new VLayout();
		main.addMembers(title, equipoForm,areaForm,adquisicionForm);		
		
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
