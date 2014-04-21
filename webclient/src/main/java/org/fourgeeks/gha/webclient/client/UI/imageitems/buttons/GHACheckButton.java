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
public class GHACheckButton extends GHAImgButton {

	/**
	 * @param clickHandler
	 */
	public GHACheckButton(ClickHandler clickHandler) {
		super("../resources/icons/check.png", clickHandler);
		setTooltip(GHAStrings.get("select"));
	}

}
