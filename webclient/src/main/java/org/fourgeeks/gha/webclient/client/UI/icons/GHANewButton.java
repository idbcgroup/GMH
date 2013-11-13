/**
 * 
 */
package org.fourgeeks.gha.webclient.client.UI.icons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class GHANewButton extends GHAImgButton {

	/**
	 * @param clickHandler
	 */
	public GHANewButton(ClickHandler clickHandler) {
		super("../resources/icons/new.png", clickHandler);
		setPrompt(GHAStrings.get("new"));
	}
}
