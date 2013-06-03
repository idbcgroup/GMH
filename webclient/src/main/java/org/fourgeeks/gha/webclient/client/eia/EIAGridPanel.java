package org.fourgeeks.gha.webclient.client.eia;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAGridPanel extends HLayout {

	public EIAGridPanel() {
		super();
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		setWidth100();
		setHeight("380px");
		// setBackgroundImage("../resources/img/tab1.jpg");
		setBackgroundColor("#E0E0E0");

		// //////Botones laterales
		VLayout botones1 = new VLayout();
		botones1.setWidth(30);
		botones1.setLayoutMargin(5);
		// botones1.setBackgroundImage("../resources/img/botonBox.jpg");
		botones1.setBackgroundColor("#E0E0E0");
		botones1.setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);
		botones1.setMembersMargin(10);
		botones1.setDefaultLayoutAlign(Alignment.CENTER);

		Img cleanImg = new Img("../resources/icons/boton3.png");
		cleanImg.setSize("20px", "20px");
		Img searchImg = new Img("../resources/icons/boton4.png");
		searchImg.setSize("20px", "20px");
		Img saveImg = new Img("../resources/icons/boton5.png");
		saveImg.setSize("20px", "20px");
		Img helpImg = new Img("../resources/icons/boton6.png");
		helpImg.setSize("20px", "20px");
		Img reloadImg = new Img("../resources/icons/boton7.png");
		reloadImg.setSize("20px", "20px");
		Img addImg = new Img("../resources/icons/boton8.png");
		addImg.setSize("20px", "20px");
		Img closeImg = new Img("../resources/icons/boton9.png");
		closeImg.setSize("20px", "20px");
		botones1.addMembers(cleanImg, searchImg, saveImg, helpImg, reloadImg,
				addImg, closeImg);
		//
		// VLayout spacer = new VLayout();
		// spacer.setHeight(10);
		// spacer.setWidth(20);

		// //////Acordeon de secciones
		// SectionStack gridStack = new SectionStack();
		// gridStack.setVisibilityMode(VisibilityMode.MUTEX);
		// gridStack.setCanDragResize(false);
		// gridStack.setWidth100();
		// gridStack.setHeight100();
		// SectionStackSection gridSection = new
		// SectionStackSection("Registros EIA");
		// gridSection.setExpanded(true);
		// gridSection.setCanCollapse(false);
		// gridSection.addItem(new EIAGrid());
		// gridStack.addSection(gridSection);

		// //////Seccion para el grid
		// SectionStackSection elementSection = new
		// SectionStackSection("Elemento Seleccionado");
		// elementSection.setExpanded(false);

		// //////Seccion inferior
		// VLayout elementContent = new VLayout();
		// elementContent.setBackgroundColor("#E0E0E0");
		// elementContent.setWidth100();
		// elementContent.setHeight(100);
		// elementSection.addItem(elementContent);
		// gridStack.addSection(elementSection);

		// //////Panel que tiene el grid+botones
		addMembers(new EIAGrid(), botones1);

		// //////Footer con paginator
		// HLayout paginatorPanel = new HLayout();
		// paginatorPanel.setWidth100();
		// paginatorPanel.setHeight("20px");
		// paginatorPanel.setStyleName("margin-paginator");
		// paginatorPanel.setAlign(Alignment.RIGHT);
		// paginatorPanel.setDefaultLayoutAlign(Alignment.CENTER);
		// paginatorPanel.setMembersMargin(7);
		//
		// Img allBackImg = new Img("../resources/icons/footBoton1.png");
		// allBackImg.setSize("14px", "10px");
		// Img backImg = new Img("../resources/icons/footBoton2.png");
		// backImg.setSize("7px", "10px");
		// Img fwdImg = new Img("../resources/icons/footBoton3.png");
		// fwdImg.setSize("7px", "10px");
		// Img allFwdImg = new Img("../resources/icons/footBoton4.png");
		// allFwdImg.setSize("14px", "10px");
		// paginatorPanel.addMembers(allBackImg,backImg,fwdImg,allFwdImg);

		// //////Inicializacion de la pagina

	}
}
