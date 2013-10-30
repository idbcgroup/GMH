package org.fourgeeks.gha.webclient.client.UI.icons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.events.ClickHandler;

public class GHACancelButton extends GHAImgButton {

	public GHACancelButton(ClickHandler clickHandler) {
		super("../resources/icons/cancel.png", clickHandler);
		setTooltip(GHAStrings.get("cancel"));
	}

}