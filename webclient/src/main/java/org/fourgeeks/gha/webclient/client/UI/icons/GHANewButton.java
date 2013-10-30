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

	public GHANewButton(ClickHandler clickHandler) {
		super("../resources/icons/new.png", clickHandler);
		setTooltip(GHAStrings.get("new"));
	}
}