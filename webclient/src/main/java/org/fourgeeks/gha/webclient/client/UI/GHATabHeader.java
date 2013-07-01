package org.fourgeeks.gha.webclient.client.UI;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.layout.HLayout;

public class GHATabHeader extends HLayout {

	private HTML titulo;
	private HTML cerrar;
	private GHATab tab;

	public GHATabHeader(GHATab tab) {
		this.tab = tab;
		setStylePrimaryName("tab-header");
		setWidth(170);
		setHeight(24);
		setDefaultLayoutAlign(VerticalAlignment.CENTER);

		titulo = new HTML();
		titulo.setWidth("160px");
		titulo.setHeight("24px");
		titulo.setStylePrimaryName("tab-header-title");
		titulo.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showTab();
			}
		});
		addMember(titulo);

		cerrar = new HTML("X");
		cerrar.setWidth("10px");
		cerrar.setHeight("24px");
		cerrar.setStylePrimaryName("tab-header-close-button");
		cerrar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				close();
			}
		});
		addMember(cerrar);
	}

	private void showTab() {
		History.newItem(tab.getToken());
	}

	private void close() {
		GHATabSet.closeTab(tab);
	}

	public void setTitle(String title) {
		titulo.setHTML(title);
	}

}
