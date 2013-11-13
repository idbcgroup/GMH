package org.fourgeeks.gha.webclient.client.UI.icons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class GHAUndoButton extends GHAImgButton {

	/**
	 * @param clickHandler
	 */
	public GHAUndoButton(ClickHandler clickHandler) {
		super("../resources/icons/undo.png", clickHandler);
		setPrompt(GHAStrings.get("undo"));
	}

}
