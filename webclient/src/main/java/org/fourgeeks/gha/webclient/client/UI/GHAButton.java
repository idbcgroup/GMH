package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickHandler;

public class GHAButton extends ImgButton {
	public GHAButton(String src) {
		setSrc(src);
		// setShowRollOver(false);
		setSize("20px", "20px");
		setShowDown(false);
	}

	public GHAButton(String src, ClickHandler clickHandler) {
		this(src);
		addClickHandler(clickHandler);
	}
}
