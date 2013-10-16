package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author alacret
 * 
 */
public class GHATabHeader extends HLayout {

	private Option titulo;

	// private GHATab tab;

	/**
	 * @param tab
	 */
	public GHATabHeader(GHATab tab) {
		// this.tab = tab;
		setStylePrimaryName("tab-header");
		setWidth100();
		setHeight(30);
		setDefaultLayoutAlign(VerticalAlignment.CENTER);

		titulo = new Option(false);
		addMember(titulo);

		addMember(new LayoutSpacer());

		Option cerrar = new Option("Cerrar", true);
		addMember(cerrar);

	}

	public void setTitle(String title) {
		titulo.setHTML(title);
	}

	@Override
	public String getTitle() {
		return titulo.getHTML();
	}

	protected static class Option extends HTML {

		public Option(boolean hoverable) {
			super();
			setStylePrimaryName("tab-header-title");
			setWidth("120px");
			// setHeight("18px");
			if (hoverable) {
				addStyleName("button-pointer");
				addMouseOverHandler(new MouseOverHandler() {

					@Override
					public void onMouseOver(MouseOverEvent event) {
						addStyleName("tab-header-title-over");
					}
				});
				addMouseOutHandler(new MouseOutHandler() {

					@Override
					public void onMouseOut(MouseOutEvent event) {
						removeStyleName("tab-header-title-over");
					}
				});
			}
		}

		public Option(String text, boolean hoverable) {
			this(hoverable);
			setHTML(text);
		}

	}
}
