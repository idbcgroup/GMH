package org.fourgeeks.gha.webclient.client.UI.icons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class GHACancelButton extends GHAImgButton {

	/**
	 * @param clickHandler
	 */
	public GHACancelButton(ClickHandler clickHandler) {
		super("../resources/icons/cancel.png", clickHandler);
		setPrompt(GHAStrings.get("cancel"));
	}

}
