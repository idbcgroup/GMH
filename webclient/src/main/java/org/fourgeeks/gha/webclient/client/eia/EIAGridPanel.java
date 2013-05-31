package org.fourgeeks.gha.webclient.client.eia;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAGridPanel extends VLayout {

	public EIAGridPanel() {
		super();
		
		VLayout botones1 = new VLayout();
		botones1.setWidth(20);
		botones1.setLayoutMargin(5);
		//botones1.setBackgroundImage("../resources/img/botonBox.jpg");
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
		botones1.addMembers(cleanImg, searchImg,saveImg,helpImg,reloadImg,addImg,closeImg);
		
		VLayout spacer = new VLayout();
		spacer.setHeight(10);
		spacer.setWidth(20);
		
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(new EIAGrid(),botones1,spacer);
		gridPanel.setWidth100();
		
		HLayout paginatorPanel = new HLayout();
		paginatorPanel.setWidth100();
		paginatorPanel.setHeight("20px");
		paginatorPanel.setStyleName("margin-paginator");
		paginatorPanel.setAlign(Alignment.RIGHT);
		paginatorPanel.setDefaultLayoutAlign(Alignment.CENTER);
		paginatorPanel.setMembersMargin(7);
		
		Img allBackImg = new Img("../resources/icons/footBoton1.png");
		allBackImg.setSize("14px", "10px");
		Img backImg = new Img("../resources/icons/footBoton2.png");
		backImg.setSize("7px", "10px");
		Img fwdImg = new Img("../resources/icons/footBoton3.png");
		fwdImg.setSize("7px", "10px");
		Img allFwdImg = new Img("../resources/icons/footBoton4.png");
		allFwdImg.setSize("14px", "10px");
		paginatorPanel.addMembers(allBackImg,backImg,fwdImg,allFwdImg);
		
		setWidth100();
		setHeight(300);
		addMembers(gridPanel,paginatorPanel);

	}	
}
