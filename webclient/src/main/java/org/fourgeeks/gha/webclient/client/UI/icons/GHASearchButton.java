package org.fourgeeks.gha.webclient.client.UI.icons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class GHASearchButton extends GHAImgButton {

	/**
	 * @param clickHandler
	 */
	public GHASearchButton(ClickHandler clickHandler) {
		super("../resources/icons/search.png", clickHandler);
		setPrompt(GHAStrings.get("search"));
		setHoverWidth(10);
	}

}
