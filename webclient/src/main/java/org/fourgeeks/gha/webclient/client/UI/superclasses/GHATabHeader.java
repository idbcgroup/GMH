package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author alacret
 * 
 */
public class GHATabHeader extends HLayout {

	private HTML titulo;

	// private GHATab tab;

	/**
	 * @param tab
	 */
	public GHATabHeader(GHATab tab) {
		// this.tab = tab;
		setStylePrimaryName("tab-header");
		setWidth(170);
		setHeight(24);
		setDefaultLayoutAlign(VerticalAlignment.CENTER);

		titulo = new HTML();
		titulo.setWidth("160px");
		titulo.setHeight("24px");
		titulo.setStylePrimaryName("tab-header-title");
		addMember(titulo);

	}

	public void setTitle(String title) {
		titulo.setHTML(title);
	}

}
