package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
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
		cerrar.setWidth("20px");
		cerrar.setHeight("24px");
		cerrar.setStylePrimaryName("tab-header-close-button");
		cerrar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				close();
			}
		});
		
		addMouseOverHandler(new MouseOverHandler() {
			
			@Override
			public void onMouseOver(MouseOverEvent event) {
				getElement().addClassName("tab-header-over");
			}
		});
		
		addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				getElement().removeClassName("tab-header-over");
			}
		});
		
		addMember(cerrar);
	}

	private void showTab() {
		selectTab();
		History.newItem(tab.getToken());
	}

	private void close() {
		GHATabSet.closeTab(tab);
	}

	public void setTitle(String title) {
		titulo.setHTML(title);
	}
	
	public void selectTab(){
		getElement().addClassName("tab-header-selected");
	}
	
	public void deselectTab(){
		getElement().removeClassName("tab-header-selected");
	}


}
