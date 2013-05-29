package org.fourgeeks.gha.webclient.client.UI;

import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.layout.HLayout;

public class GHATabHeader extends HLayout {

	private HTML titulo;
	private HTML cerrar;

	public GHATabHeader() {
		setStylePrimaryName("tab-header");
		setWidth(170);
		setHeight(24);
		setDefaultLayoutAlign(VerticalAlignment.CENTER);

		titulo = new HTML();
		titulo.setWidth("160px");
		titulo.setHeight("24px");
		titulo.setStylePrimaryName("tab-header-title");
		addMember(titulo);

		cerrar = new HTML("X");
		cerrar.setWidth("10px");
		cerrar.setHeight("24px");
		cerrar.setStylePrimaryName("tab-header-close-button");
		addMember(cerrar);
	}

	public void setTitle(String title) {
		titulo.setHTML(title);
	}

}
