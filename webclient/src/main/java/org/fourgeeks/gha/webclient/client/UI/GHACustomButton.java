package org.fourgeeks.gha.webclient.client.UI;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;

/**
 * @author alacret
 * 
 */
public class GHACustomButton extends Composite implements HasClickHandlers {
	private GHAImgButton button;

	public GHACustomButton() {
		button = new GHAImgButton("../resources/icons/new.png");
		initWidget(button);
		// widget.setWidget(w);

	}

	@Override
	public HandlerRegistration addClickHandler(
			com.google.gwt.event.dom.client.ClickHandler handler) {
		;
		return button.addDomHandler(handler,
				com.google.gwt.event.dom.client.ClickEvent.getType());
	}
}
