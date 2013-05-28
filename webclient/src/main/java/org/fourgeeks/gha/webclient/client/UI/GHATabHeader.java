package org.fourgeeks.gha.webclient.client.UI;

import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.layout.HLayout;

public class GHATabHeader extends HLayout {

	public GHATabHeader(GHATab tab) {
		HTML titulo = new HTML(tab.getTitle());
		titulo.setHeight("24px");
		titulo.setStylePrimaryName("tab-title");
		setHeight(24);
		addMember(titulo);
		
		HTML cerrar = new HTML("X");
		cerrar.setHeight("24px");
		cerrar.setWidth("10px");
		cerrar.setStylePrimaryName("tab-close-button");
		setHeight(24);
		addMember(cerrar);
		
		setDefaultLayoutAlign(VerticalAlignment.CENTER);
	}

}
