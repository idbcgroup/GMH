package org.fourgeeks.gha.webclient.client.UI.icons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.events.ClickHandler;

public class GHAUndoButton extends GHAImgButton {

	public GHAUndoButton(ClickHandler clickHandler) {
		super("../resources/icons/undo.png", clickHandler);
		setTooltip(GHAStrings.get("undo"));
	}

}
