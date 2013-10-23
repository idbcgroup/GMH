package org.fourgeeks.gha.webclient.client.UI.superclasses;

import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class GHAImgButton extends ImgButton {
	/**
	 * @param src
	 */
	public GHAImgButton(String src) {
		setSrc(src);
		setSize("20px", "20px");
		setShowDown(false);
	}

	/**
	 * @param src
	 * @param clickHandler
	 */
	public GHAImgButton(String src, ClickHandler clickHandler) {
		this(src);
		addClickHandler(clickHandler);
	}
}
