package org.fourgeeks.gha.webclient.client.UI.icons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class GHACleanButton extends GHAImgButton {

	/**
	 * @param src
	 * @param clickHandler
	 */
	public GHACleanButton(ClickHandler clickHandler) {
		super("../resources/icons/clean.png", clickHandler);
		setTooltip(GHAStrings.get("clean"));
	}

}
