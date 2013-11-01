package org.fourgeeks.gha.webclient.client.UI.icons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.events.ClickHandler;

public class GHAEditButton extends GHAImgButton {

	public GHAEditButton(ClickHandler clickHandler) {
		super("../resources/icons/edit.png", clickHandler);
		setTooltip(GHAStrings.get("edit"));
	}

}
