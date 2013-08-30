package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickHandler;

public class GHAImgButton extends ImgButton {
	public GHAImgButton(String src) {
		setSrc(src);
		// setShowRollOver(false);
		setSize("20px", "20px");
		setShowDown(false);
	}

	public GHAImgButton(String src, ClickHandler clickHandler) {
		this(src);
		addClickHandler(clickHandler);
	}
}
