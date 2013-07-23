package org.fourgeeks.gha.webclient.client.UI;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;

public class GHACustomButton extends Composite implements HasClickHandlers{
	public GHACustomButton() {
	      DecoratorPanel widget = new DecoratorPanel();
	      initWidget(widget);
	      widget.setWidget(new GHAButton("../resources/icons/new.png"));
	     // widget.setSize("15px","15px");
	    }
	   
		@Override
		public HandlerRegistration addClickHandler(
				com.google.gwt.event.dom.client.ClickHandler handler) {
			return addDomHandler(handler, com.google.gwt.event.dom.client.ClickEvent.getType());
		}	
}
