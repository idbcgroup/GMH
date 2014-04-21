/**
 * 
 */
package org.fourgeeks.gha.webclient.client.UI.icons.buttons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class GHACopyButton extends GHAImgButton {

	/**
	 * @param clickHandler
	 */
	public GHACopyButton(ClickHandler clickHandler) {
		super("../resources/icons/copy.png", clickHandler);
		setPrompt(GHAStrings.get("copy"));
	}

}
