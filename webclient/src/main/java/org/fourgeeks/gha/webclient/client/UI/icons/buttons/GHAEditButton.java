package org.fourgeeks.gha.webclient.client.UI.icons.buttons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class GHAEditButton extends GHAImgButton {

	/**
	 * @param clickHandler
	 */
	public GHAEditButton(ClickHandler clickHandler) {
		super("../resources/icons/edit.png", clickHandler);
		setPrompt(GHAStrings.get("edit"));
	}

}
