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
public class GHADeleteButton extends GHAImgButton {

	/**
	 * @param clickHandler
	 */
	public GHADeleteButton(ClickHandler clickHandler) {
		super("../resources/icons/delete.png", clickHandler);
		setPrompt(GHAStrings.get("delete"));
	}

}
