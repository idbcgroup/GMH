package org.fourgeeks.gha.webclient.client.UI.icons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.events.ClickHandler;

public class GHASearchButton extends GHAImgButton {

	public GHASearchButton(ClickHandler clickHandler) {
		super("../resources/icons/search.png", clickHandler);
		setTooltip(GHAStrings.get("search"));
	}

}