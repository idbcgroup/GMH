package org.fourgeeks.gha.webclient.client.edt;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

public class EDTTopMenu extends HLayout{

	public EDTTopMenu() {
		
		setWidth100();
		setHeight("40px");
		setMembersMargin(8);
		setStyleName("sides-padding");
		setBackgroundColor("#EAEAEA");
		setDefaultLayoutAlign(Alignment.CENTER);
		
		IButton reassignBut = new IButton("Reasignar");
		
		IButton declineBut = new IButton("Declinar");
		
		IButton finishBut = new IButton("Terminar");
		
		IButton newBut = new IButton("Nueva Cita");
		
		IButton registerBut = new IButton("Registrar Cita");
		
		addMembers(reassignBut,declineBut,finishBut,new LayoutSpacer(),newBut,registerBut);
	}

}
