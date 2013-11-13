package org.fourgeeks.gha.webclient.client.UI.icons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class GHACloseButton extends GHAImgButton {

	/**
	 * @param src
	 * @param clickHandler
	 */
	public GHACloseButton(ClickHandler clickHandler) {
		super("../resources/icons/cancel.png", clickHandler);
		setPrompt(GHAStrings.get("cancel"));
	}

}
