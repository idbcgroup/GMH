package org.fourgeeks.gha.webclient.client.UI.imageitems.buttons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.imageitems.GHAImgButton;

import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class GHASaveButton extends GHAImgButton {

	/**
	 * @param clickHandler
	 */
	public GHASaveButton(ClickHandler clickHandler) {
		super("../resources/icons/save.png", clickHandler);
		setPrompt(GHAStrings.get("save"));
	}

}
