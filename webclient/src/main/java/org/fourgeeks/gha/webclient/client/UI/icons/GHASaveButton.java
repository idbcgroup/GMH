package org.fourgeeks.gha.webclient.client.UI.icons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.events.ClickHandler;

public class GHASaveButton extends GHAImgButton {

	public GHASaveButton(ClickHandler clickHandler) {
		super("../resources/icons/save.png", clickHandler);
		setTooltip(GHAStrings.get("save"));
	}

}
