/**
 * 
 */
package org.fourgeeks.gha.webclient.client.UI.imageitems.buttons;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.imageitems.GHAImgButton;

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
