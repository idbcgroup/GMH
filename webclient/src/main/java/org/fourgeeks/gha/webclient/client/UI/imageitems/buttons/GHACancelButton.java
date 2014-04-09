package org.fourgeeks.gha.webclient.client.UI.imageitems.buttons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.imageitems.GHAImgButton;

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
